package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.database.DatabaseCleaner;
import lv.velexauto.velex.database.UserDAO;
import lv.velexauto.velex.domain.User;
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
public class UserDAOImplTest extends DAOImplTest {

    @Autowired
    @Qualifier("HibernateDAOUser")
    private UserDAO userDAO;

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
        User userFromDB = userDAO.getById(user.getUserId());

        assertNotNull(userFromDB);
        assertEquals(user.getLogin(), userFromDB.getLogin());
        assertEquals(user.getPassword(), userFromDB.getPassword());
        assertEquals(user.getName(), userFromDB.getName());
        assertEquals(user.getSurname(), userFromDB.getSurname());
        assertEquals(user.getUserId(), userFromDB.getUserId());
    }

    @Test
    @Transactional
    public void testUpdate() throws DBException {

        User user = new User("Dinjvald", "besdenlac29", "Deniss", "Beskorovoainijs");
        userDAO.create(user);
        user.setLogin("Daster");
        user.setPassword("192rgk1dwa");
        user.setName("Sergey");
        user.setSurname("Doro");
        userDAO.update(user);
        User userFromDB = userDAO.getById(user.getUserId());

        assertNotNull(userFromDB);
        assertEquals(user.getLogin(), userFromDB.getLogin());
        assertEquals(user.getPassword(), userFromDB.getPassword());
        assertEquals(user.getName(), userFromDB.getName());
        assertEquals(user.getSurname(), userFromDB.getSurname());
        assertEquals(user.getUserId(), userFromDB.getUserId());
    }

    @Test
    @Transactional
    public void testDeleteAndGetAll() throws DBException {
        User user1 = new User("Dinjvald", "besdenlac29", "Deniss", "Beskorovoainijs");
        User user2 = new User("Daster", "192rgk1dwa", "Sergey", "Doro");
        userDAO.create(user1);
        userDAO.create(user2);
        userDAO.delete(user1.getUserId());
        List <User> usersFromDB = userDAO.getAll();

        assertNotNull(usersFromDB);
        assertEquals(1, usersFromDB.size());
    }

}
