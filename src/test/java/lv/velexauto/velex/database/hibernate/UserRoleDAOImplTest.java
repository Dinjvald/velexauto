
package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.HelperClasses.DateAssistant;
import lv.velexauto.velex.database.*;
import lv.velexauto.velex.domain.Company;
import lv.velexauto.velex.domain.Employee;
import lv.velexauto.velex.domain.User;
import lv.velexauto.velex.domain.UserRole;
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
public class UserRoleDAOImplTest extends DAOImplTest {

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
    @Qualifier("HibernateDAOUserRole")
    private UserRoleDAO userRoleDAO;

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
        UserRole userRole = createFistUserRole(user);
        userRoleDAO.create(userRole);

        UserRole userRoleFromDB = userRoleDAO.getById(userRole.getUserRoleId());

        testWithAssertEquals(userRole, userRoleFromDB);
    }

    @Test
    @Transactional
    public void testUpdate() throws DBException, ParseException {

        Company company = createFirstCompany();
        companyDAO.create(company);
        Employee employee = createFirstEmployee(company);
        employeeDAO.create(employee);
        User user1 = createFirstUser(employee);
        userDAO.create(user1);
        User user2 = createSecondUser(employee);
        userDAO.create(user2);
        UserRole userRole = createFistUserRole(user1);
        userRoleDAO.create(userRole);
        UserRole userRoleFromDB = userRoleDAO.getById(userRole.getUserRoleId());

        assertEquals(userRole.getUserRoleId(), userRoleFromDB.getUserRoleId());

        userRole = updateUserRoleWithNewData(userRole, user2);
        userRoleDAO.update(userRole);
        userRoleFromDB = userRoleDAO.getById(userRole.getUserRoleId());

        testWithAssertEquals(userRole, userRoleFromDB);
    }

    @Test
    @Transactional
    public void testDelete() throws DBException, ParseException {

        Company company = createFirstCompany();
        companyDAO.create(company);
        Employee employee = createFirstEmployee(company);
        employeeDAO.create(employee);
        User user = createFirstUser(employee);
        userDAO.create(user);
        UserRole userRole1 = createFistUserRole(user);
        userRoleDAO.create(userRole1);
        UserRole userRole2 = createSecondUserRole(user);
        userRoleDAO.create(userRole2);
        List<UserRole> list = userRoleDAO.getAll();

        assertEquals(2, list.size());

        userRoleDAO.delete(userRole1.getUserRoleId());

        list = userRoleDAO.getAll();

        assertEquals(1, list.size());
    }

    @Test
    @Transactional
    public void testGelAll() throws ParseException, DBException {

        Company company = createFirstCompany();
        companyDAO.create(company);
        Employee employee = createFirstEmployee(company);
        employeeDAO.create(employee);
        User user = createFirstUser(employee);
        userDAO.create(user);
        UserRole userRole1 = createFistUserRole(user);
        userRoleDAO.create(userRole1);
        UserRole userRole2 = createSecondUserRole(user);
        userRoleDAO.create(userRole2);
        UserRole userRole3 = createThirdUserRole(user);
        userRoleDAO.create(userRole3);

        List<UserRole> list = userRoleDAO.getAll();

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

    private User createFirstUser(Employee employee) {

        return new User(employee, "admin", "admin");
    }

    private User createSecondUser(Employee employee) {

        return new User(employee, "user", "user");
    }

    private UserRole createFistUserRole(User user) {

        return new UserRole("ROLE_ADMIN", user);
    }

    private UserRole createSecondUserRole(User user) {

        return new UserRole("ROLE_USER", user);
    }

    private UserRole createThirdUserRole(User user) {

        return new UserRole("ROLE_VISIT", user);
    }

    private void testWithAssertEquals(UserRole userRole, UserRole userRoleFromDB) {

        assertEquals(userRole.getUserRoleId(), userRoleFromDB.getUserRoleId());
        assertEquals(userRole.getUser(), userRoleFromDB.getUser());
        assertEquals(userRole.getRole(), userRoleFromDB.getRole());
    }

    private UserRole updateUserRoleWithNewData(UserRole userRole, User user) {

        userRole.setRole("ROLE_TEST");
        userRole.setUser(user);
        return userRole;
    }
}

