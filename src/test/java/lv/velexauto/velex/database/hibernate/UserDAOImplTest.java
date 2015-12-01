package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.HelperClasses.DateAssistant;
import lv.velexauto.velex.database.*;
import lv.velexauto.velex.domain.Company;
import lv.velexauto.velex.domain.Employee;
import lv.velexauto.velex.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Dinjvald on 12/04/15.
 */

@WebAppConfiguration
public class UserDAOImplTest extends DAOImplTest {

    @Autowired
    @Qualifier("HibernateDAOCompany")
    private CompanyDAO companyDAO;

    @Autowired
    @Qualifier("HibernateDAOEmployee")
    private EmployeeDAO employeeDAO;

    @Autowired
    @Qualifier("HibernateDAOUser")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("DateAssistant")
    private DateAssistant dateAssistant;

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    @Transactional
    public void testCreate() throws DBException, ParseException {

        Company company = createFirstCompany();
        companyDAO.create(company);
        Employee employee = createFirstEmployee(company);
        employeeDAO.create(employee);
        User user = createFirstUser(employee);
        userDAO.create(user);

        User userFromDB = userDAO.getById(user.getUserId());

       testWithAssertEquals(user, userFromDB);
    }

    @Test
    @Transactional
    public void testUpdate() throws DBException, ParseException {

        Company company = createFirstCompany();
        companyDAO.create(company);
        Employee employee1 = createFirstEmployee(company);
        employeeDAO.create(employee1);
        Employee employee2 = createSecondEmployee(company);
        employeeDAO.create(employee2);
        User user = createFirstUser(employee1);
        userDAO.create(user);
        User userFromDB = userDAO.getById(user.getUserId());

        assertEquals(user.getUserId(), userFromDB.getUserId());

        user = updateUserWithNewData(user, employee2);
        userDAO.update(user);
        userFromDB = userDAO.getById(user.getUserId());

        testWithAssertEquals(user, userFromDB);
    }

    @Test
    @Transactional
    public void testDelete() throws DBException, ParseException {

        Company company = createFirstCompany();
        companyDAO.create(company);
        Employee employee = createFirstEmployee(company);
        employeeDAO.create(employee);
        User user1 = createFirstUser(employee);
        userDAO.create(user1);
        User user2 = createSecondUser(employee);
        userDAO.create(user2);
        List<User> list = userDAO.getAll();

        assertEquals(2, list.size());

        userDAO.delete(user1.getUserId());
        list = userDAO.getAll();

        assertEquals(1, list.size());
    }

    @Test
    @Transactional
    public void testGetAll() throws ParseException, DBException {

        Company company = createFirstCompany();
        companyDAO.create(company);
        Employee employee = createFirstEmployee(company);
        employeeDAO.create(employee);
        User user1 = createFirstUser(employee);
        userDAO.create(user1);
        User user2 = createSecondUser(employee);
        userDAO.create(user2);
        User user3 = createThirdUser(employee);
        userDAO.create(user3);

        List<User> list = userDAO.getAll();

        assertEquals(3, list.size());

    }

    private Company createFirstCompany() throws ParseException {

        return new Company.CompanyBuilder()
                .name("SIA VelexAuto")
                .registrationNr("45403038976")
                .vatNr("LV45403038976")
                .establishmentDate(dateAssistant.stringToDate("14.01.2014"))
                .registrationDate(dateAssistant.stringToDate("23.11.2015"))
                .address("Lāčplēša iela 29-36, Aizkraukle, Latvia, LV-5101")
                .legalAddress("Lāčplēša iela 29-36, Aizkraukle, Latvia, LV-5101")
                .telephoneNr("+371 26936982")
                .eMail("velexvladimir@gmail.com")
                .web("www.velexauto.lv")
                .build();
    }

    private Employee createFirstEmployee(Company company) {

        return new Employee.EmployeeBuilder()
                .company(company)
                .name("Deniss")
                .surname("Beskorovainijs")
                .passportNr("LV123456789")
                .personalCode("030988-11868")
                .eMail("dinjab@gmail.com")
                .telephoneNr("+37126936982")
                .isActive(true)
                .build();
    }

    private Employee createSecondEmployee(Company company) {

        return new Employee.EmployeeBuilder()
                .company(company)
                .name("Sergey")
                .surname("Dorosenko")
                .passportNr("LV987654321")
                .personalCode("220786-58621")
                .eMail("doro@inbox.lv")
                .telephoneNr("+371 269398")
                .isActive(true)
                .build();
    }

    private User createFirstUser(Employee employee) {

        return new User(employee, "admin", "admin");
    }

    private User createSecondUser(Employee employee) {

        return new User(employee, "user", "user");
    }

    private User createThirdUser(Employee employee) {

        return new User(employee, "Dinjval", "daster");
    }

    private void testWithAssertEquals(User user, User userFromDB) {

        assertEquals(user.getUserId(), userFromDB.getUserId());
        assertEquals(user.getEmployee(), userFromDB.getEmployee());
        assertEquals(user.getLogin(), userFromDB.getLogin());
        assertEquals(user.getPassword(), userFromDB.getPassword());
    }

    private User updateUserWithNewData(User user, Employee employee) {

        user.setEmployee(employee);
        user.setLogin("RedDevil");
        user.setPassword("doro");
        return user;
    }

}

