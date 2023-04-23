package cn.lanqiao.utils;

import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DButils {
    private static final String url;
    private static final String username;
    private static final String password;

    static {
        //获取DBConfig.properties中的数据
        Properties p = new Properties();
        try {
            p.load(DButils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driverName = p.getProperty("driverName");
        url = p.getProperty("url");
        username = p.getProperty("userName");
        password = p.getProperty("passWord");

        try {
            //加载驱动包
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    //获取Connection对象
    public static Connection getConnection() throws SQLException {

        Connection connection = local.get();

        if (connection == null) {
            connection = DriverManager.getConnection(url, username, password);

            //设置事务隔离级别
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            local.set(connection);
        }

        return connection;
    }

    //关闭资源
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                //判断连接是否开启事务
                if (connection.getAutoCommit()) {//没有开启
                    connection.close();
                    local.set(null);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static final ThreadLocal<Connection> local = new ThreadLocal<>();

    // 开启事务
    public static void startTransaction() throws SQLException {
        //获取连接对象
        Connection connection = getConnection();
        //开启事务
        connection.setAutoCommit(false);

    }

    //提交事务
    public static void commit() throws SQLException {
        Connection connection = local.get();
        connection.commit();
        connection.close();
        local.set(null);
    }

    //回滚事务
    public static void rollback() throws SQLException {
        Connection connection = local.get();
        connection.rollback();
        connection.close();
        local.set(null);
    }




    //insert into student(name,sex,age,....) values(?,?,?,?,?)
    //inset into user(username,password) values(?,?)

    //主键回填
    public static int commonInsert(String sql, Object... param) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            //设置sql语句，设置主键回填
            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            //设置参数
            for (int i = 0; i < param.length; i++) {
                statement.setObject(i + 1, param[i]);
            }
            statement.executeUpdate();

            //获取主键
            resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                int primaryKey = resultSet.getInt(1);
                return primaryKey;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButils.close(connection, statement, null);
        }
        return -1;
    }



    /**
     * 根据条件查询总数
     * @param sql
     * @param param
     * @return
     */
    public static int commonQueryCount(String sql, Object... param) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = getConnection();
            //设置sql语句
            statement = connection.prepareStatement(sql);
            //设置参数
            for (int i = 0; i < param.length; i++) {
                statement.setObject(i + 1, param[i]);
            }
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButils.close(connection, statement, resultSet);
        }
        return count;
    }



    public static ArrayList<Integer> commonQueryCountAll(String sql, Object... param) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int count = 0;
        //int month = 0;
        ArrayList<Integer> list = new ArrayList<>();
        try {
            connection = getConnection();
            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                //count = resultSet.getInt(1);
                count = resultSet.getInt("count");
                list.add(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButils.close(connection, statement, resultSet);
        }
        return list;
    }


    //更新方法(添加、删除、修改)
    public static int commonUpdate(String sql, Object... param) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < param.length; i++) {
                statement.setObject(i + 1, param[i]);
            }
            int num = statement.executeUpdate();
            return num;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //查询数据
    public static <T> ArrayList<T> commonQuery(Class<T> clazz, String sql, Object... param) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            //设置参数
            for (int i = 0; i < param.length; i++) {
                statement.setObject(i + 1, param[i]);
            }
            resultSet = statement.executeQuery();

            //获取表信息
            ResultSetMetaData metaData = resultSet.getMetaData();
            //获取字段个数
            int columnCount = metaData.getColumnCount();
            ArrayList<T> list = new ArrayList<>();

            while (resultSet.next()) {//判断是否有迭代的数据行 （5-周华健-40-13000-Java）

                //利用反射创建对象
                T t = clazz.newInstance();

                //遍历获取字段名
                for (int i = 1; i <= columnCount; i++) {
                    //获取字段名
                    String name = metaData.getColumnName(i);
                    //获取对应字段名上的数据
                    Object value = resultSet.getObject(name);

                    //获取对象中的属性对象
                    Field field = getField(clazz, name);
                    if (field != null) {
                        //设置修改权限
                        field.setAccessible(true);
                        //利用反射向对象设置属性
                        field.set(t, value);
                    }
                }
                list.add(t);
            }
            return list;
        } catch (SQLException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            //关闭
            DButils.close(connection, statement, resultSet);
        }
        return null;
    }

    //获取类上的属性对象
    public static Field getField(Class<?> clazz, String name) {
        Field field = null;
        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            try {
                field = c.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
            }
        }
        return field;
    }
}
