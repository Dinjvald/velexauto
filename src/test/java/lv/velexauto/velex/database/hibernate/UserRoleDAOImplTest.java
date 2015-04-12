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

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Dinjvald on 12/04/15.
 */

@WebAppConfiguration
public class UserRoleDAOImplTest extends DAOImplTest {

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
    public void testCreateAndGetById() throws DBException {
        User user = new User("Dinjvald", "besdenlac29", "Deniss", "Beskorovoainijs");
        userDAO.create(user);
        UserRole userRole = new UserRole("ADMIN", user);
        userRoleDAO.create(userRole);
        UserRole userRoleFromDB = userRoleDAO.getById(userRole.getUserRoleId());

        assertNotNull(userRoleFromDB);
        assertEquals(userRole.getRole(), userRoleFromDB.getRole());
        assertEquals(userRole.getUserRoleId(), userRoleFromDB.getUserRoleId());
        assertEquals(userRole.getUser().getUserId(), userRoleFromDB.getUser().getUserId());
        assertEquals(userRole.getUser().getName(), userRoleFromDB.getUser().getName());
    }

    @Test
    @Transactional
    public void testUpdate() throws DBException {
        User user = new User("Dinjvald", "besdenlac29", "Deniss", "Beskorovoainijs");
        userDAO.create(user);
        UserRole userRole = new UserRole("ADMIN", user);
        userRoleDAO.create(userRole);
        userRole.setRole("USER");
        userRoleDAO.update(userRole);
        UserRole userRoleFromDB = userRoleDAO.getById(userRole.getUserRoleId());

        assertNotNull(userRoleFromDB);
        assertEquals(userRole.getRole(), userRoleFromDB.getRole());
        assertEquals(userRole.getUserRoleId(), userRoleFromDB.getUserRoleId());
        assertEquals(userRole.getUser().getUserId(), userRoleFromDB.getUser().getUserId());
    }

    @Test
    @Transactional
    public void testDeleteAndGetAll() throws DBException {
        User user1 = new User("Dinjvald", "besdenlac29", "Deniss", "Beskorovoainijs");
        User user2 = new User("Daster", "192rgk1dwa", "Sergey", "Doro");
        userDAO.create(user1);
        userDAO.create(user2);
        UserRole userRole1 = new UserRole("ADMIN", user1);
        UserRole userRole2 = new UserRole("USER", user2);
        userRoleDAO.create(userRole1);
        userRoleDAO.create(userRole2);
        List <UserRole> userRoles = userRoleDAO.getAll();

        assertEquals(2, userRoles.size());

        userRoleDAO.delete(userRole1.getUserRoleId());
        userRoles = userRoleDAO.getAll();

        assertEquals(1, userRoles.size());
    }
}
