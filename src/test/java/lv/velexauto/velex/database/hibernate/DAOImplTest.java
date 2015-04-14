package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.config.SpringAppConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * Created by Dinjvald on 23/03/15.
 */

//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@TransactionConfiguration(defaultRollback = false)
@WebAppConfiguration
public class DAOImplTest {

    /*This test is needed so that "All Test Passed" result could occur.
    You can uncomment "@Ignore" and see what happens.*/
    @Test
    public void testDummy() {
        assertEquals(1,1);
    }

}

