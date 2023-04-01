import cn.lanqiao.dao.ReportDao;
import cn.lanqiao.dao.impl.ReportDaoImpl;
import org.junit.Test;

public class ReportDaoImplTest {
    ReportDao reportDao = new ReportDaoImpl();
    @Test
    public void test1(){
        int countBySupplierId = reportDao.getCountBySupplierId("2");
        System.out.println(countBySupplierId);
    }
}
