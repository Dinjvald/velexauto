package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.HelperClasses.DateAssistant;
import lv.velexauto.velex.database.CompanyDAO;
import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.database.DatabaseCleaner;
import lv.velexauto.velex.database.EmployeeDAO;
import lv.velexauto.velex.domain.Company;
import lv.velexauto.velex.domain.Employee;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dinjvald on 24/11/15.
 */
@WebAppConfiguration
public class EmployeeDAOImplTest extends DAOImplTest {

    @Autowired
    @Qualifier("HibernateDAOEmployee")
    private EmployeeDAO employeeDAO;

    @Autowired
    @Qualifier("HibernateDAOCompany")
    private CompanyDAO companyDAO;

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
    public void testCreate() throws ParseException, DBException {

        Company company = createFirstCompany();
        companyDAO.create(company);

        Employee employee = createFirstEmployee(company);
        employeeDAO.create(employee);
        Employee employeeFromDB = employeeDAO.getById(employee.getEmployeeId());

        testWithAssertEquals(employee, employeeFromDB);
    }

    @Test
    @Transactional
    public void testUpdate() throws ParseException, DBException {

        Company company1 = createFirstCompany();
        companyDAO.create(company1);
        Company company2 = createSecondCompany();
        companyDAO.create(company2);

        Employee employee = createFirstEmployee(company1);
        employeeDAO.create(employee);
        Employee employeeFromDB = employeeDAO.getById(employee.getEmployeeId());

        assertEquals(employee.getEmployeeId(), employeeFromDB.getEmployeeId());

        employee = updateEmployeeWithNewData(employee, company2);
        employeeDAO.update(employee);
        employeeFromDB = employeeDAO.getById(employee.getEmployeeId());

        testWithAssertEquals(employee, employeeFromDB);
    }

    @Test
    @Transactional
    public void testDelete() throws ParseException, DBException {

        Company company = createFirstCompany();
        companyDAO.create(company);

        Employee employee1 = createFirstEmployee(company);
        Employee employee2 = createSecondEmployee(company);
        employeeDAO.create(employee1);
        employeeDAO.create(employee2);
        List<Employee> list = employeeDAO.getAll();

        assertEquals(2, list.size());

        employeeDAO.delete(employee1.getEmployeeId());
        list = employeeDAO.getAll();

        assertEquals(1, list.size());
    }

    @Test
    @Transactional
    public void testGelAll() throws ParseException, DBException {

        Company company = createFirstCompany();
        companyDAO.create(company);

        Employee employee1 = createFirstEmployee(company);
        Employee employee2 = createSecondEmployee(company);
        Employee employee3 = createThirdEmployee(company);
        employeeDAO.create(employee1);
        employeeDAO.create(employee2);
        employeeDAO.create(employee3);

        List<Employee> list = employeeDAO.getAll();

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

    private Company createSecondCompany() throws ParseException {

        return new Company.CompanyBuilder()
                .name("SIA Kreiss")
                .registrationNr("123456789")
                .vatNr("LV123456789")
                .establishmentDate(dateAssistant.stringToDate("01.01.2010"))
                .registrationDate(dateAssistant.stringToDate("01.11.2015"))
                .address("Kurzemes pr. 102-25")
                .legalAddress("Maskavas iela 250-1")
                .telephoneNr("+371 29130977")
                .eMail("dinjab@gmail.com")
                .web("www.kreiss.lv")
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

    private Employee createThirdEmployee(Company company) {

        return new Employee.EmployeeBuilder()
                .company(company)
                .name("Viktor")
                .surname("Avgucevic")
                .passportNr("LV456987123")
                .personalCode("239678-26987")
                .eMail("viktor@bilatris.lv")
                .telephoneNr("+371 26983645")
                .isActive(true)
                .build();
    }

    private Employee updateEmployeeWithNewData(Employee employee, Company company) {

        employee.setCompany(company);
        employee.setName("Sergey");
        employee.setSurname("Dorosenko");
        employee.setPassportNr("LV987654321");
        employee.setPersonalCode("263597-15869");
        employee.seteMail("doro@inbox.lv");
        employee.setTelephoneNr("+3712986348");
        return employee;
    }

    private void testWithAssertEquals(Employee employee, Employee employeeFromDB) {

        assertEquals(employee.getEmployeeId(), employeeFromDB.getEmployeeId());
        assertEquals(employee.getCompany().getCompanyId(), employeeFromDB.getCompany().getCompanyId());
        assertEquals(employee.getName(), employeeFromDB.getName());
        assertEquals(employee.getSurname(), employeeFromDB.getSurname());
        assertEquals(employee.getPassportNr(), employeeFromDB.getPassportNr());
        assertEquals(employee.getPersonalCode(), employeeFromDB.getPersonalCode());
        assertEquals(employee.geteMail(), employeeFromDB.geteMail());
        assertEquals(employee.getTelephoneNr(), employeeFromDB.getTelephoneNr());
        assertEquals(employee.isActive(), employeeFromDB.isActive());
    }

}
