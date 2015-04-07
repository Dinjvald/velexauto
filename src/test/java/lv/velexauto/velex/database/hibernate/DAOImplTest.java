package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.config.SpringAppConfig;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by Dinjvald on 23/03/15.
 */

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAppConfig.class)
@TransactionConfiguration(defaultRollback = false)
public class DAOImplTest {

}

