/*
package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.database.DatabaseCleaner;
import lv.velexauto.velex.database.UserDAO;
import lv.velexauto.velex.database.UserRoleDAO;
import lv.velexauto.velex.domain.User;
import lv.velexauto.velex.domain.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

*/
/**
 * Created by Dinjvald on 29/10/15.
 *//*


@WebAppConfiguration
public class CreateNewUserTest extends DAOImplTest {

    @Autowired
    @Qualifier("HibernateDAOUser")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("HibernateDAOUserRole")
    private UserRoleDAO userRoleDAO;

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }
    @Test
    @Transactional
    public void newUserTest() throws DBException {

        User newUser = new User("dinjab", "besdenlac29", "Deniss", "Besk");
        userDAO.create(newUser);

        UserRole newUserRole = new UserRole("ROLE_ADMIN", newUser);
        userRoleDAO.create(newUserRole);
        newUserRole = new UserRole("ROLE_RANDOM", newUser);
        userRoleDAO.create(newUserRole);
    }

}
*/
