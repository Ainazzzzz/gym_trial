package peaksoft;

import org.junit.jupiter.api.Test;

public class HibernateUtilTest {

    @Test
    public void testGetSessionFactory() {
        peaksoft.config.HibernateUtil.getSession();
    }
}
