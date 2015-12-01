package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.HelperClasses.DateAssistant;
import lv.velexauto.velex.database.CompanyDAO;
import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.database.DatabaseCleaner;
import lv.velexauto.velex.domain.Company;
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
 * Created by Dinjvald on 23/11/15.
 */
@WebAppConfiguration
public class CompanyDAOImplTest extends DAOImplTest {

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
    public void testCreate() throws DBException, ParseException {

        Company company = createFirstCompany();

        companyDAO.create(company);

        Company companyFromDB = companyDAO.getById(company.getCompanyId());

        testWithAssertEquals(company, companyFromDB);
    }

    @Test
    @Transactional
    public void testUpdate() throws DBException, ParseException {

        Company company = createFirstCompany();
        companyDAO.create(company);
        Company companyFromDB = companyDAO.getById(company.getCompanyId());

        assertEquals(company.getCompanyId(), companyFromDB.getCompanyId());

        company = updateCompanyWithNewData(company);
        companyDAO.update(company);
        companyFromDB = companyDAO.getById(company.getCompanyId());

        testWithAssertEquals(company, companyFromDB);
    }

    @Test
    @Transactional
    public void testDelete() throws DBException, ParseException {

        Company company1 = createFirstCompany();
        Company company2 = createSecondCompany();
        companyDAO.create(company1);
        companyDAO.create(company2);
        List<Company> list = companyDAO.getAll();

        assertEquals(2, list.size());

        companyDAO.delete(company1.getCompanyId());
        list = companyDAO.getAll();

        assertEquals(1, list.size());
    }

    @Test
    @Transactional
    public void testGetAll() throws ParseException, DBException {

        Company company1 = createFirstCompany();
        Company company2 = createSecondCompany();
        Company company3 = createThirdCompany();

        companyDAO.create(company1);
        companyDAO.create(company2);
        companyDAO.create(company3);

        List<Company> list = companyDAO.getAll();

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

    private Company createThirdCompany() throws ParseException {

        return new Company.CompanyBuilder()
                .name("SIA Bilatris")
                .registrationNr("987654321")
                .vatNr("LV987654321")
                .establishmentDate(dateAssistant.stringToDate("25.08.2011"))
                .registrationDate(dateAssistant.stringToDate("20.09.2015"))
                .address("Paula Lejina 45")
                .legalAddress("Marupes novads 67")
                .telephoneNr("+371 25794596")
                .eMail("bes-lena@inbox.lv")
                .web("www.bilatris.lv")
                .build();
    }

    private Company updateCompanyWithNewData(Company company) throws ParseException{

        company.setName("SIA Kreiss");
        company.setRegistrationNr("123456789");
        company.setVatNr("LV123456789");
        company.setEstablishmentDate(dateAssistant.stringToDate("01.01.2010"));
        company.setRegistrationDate(dateAssistant.stringToDate("01.11.2015"));
        company.setAddress("Kurzemes pr. 102-25");
        company.setLegalAddress("Maskavas iela 250-1");
        company.setTelephoneNr("+371 29130977");
        company.seteMail("dinjab@gmail.com");
        company.setWeb("www.kreiss.lv");
        return company;
    }

    private void testWithAssertEquals(Company company, Company companyFromDB) {

        assertEquals(company.getCompanyId(), companyFromDB.getCompanyId());
        assertEquals(company.getName(), companyFromDB.getName());
        assertEquals(company.getRegistrationNr(), companyFromDB.getRegistrationNr());
        assertEquals(company.getVatNr(), companyFromDB.getVatNr());
        assertEquals(company.getEstablishmentDate(), companyFromDB.getEstablishmentDate());
        assertEquals(company.getRegistrationDate(), companyFromDB.getRegistrationDate());
        assertEquals(company.getAddress(), companyFromDB.getAddress());
        assertEquals(company.getLegalAddress(), companyFromDB.getLegalAddress());
        assertEquals(company.getTelephoneNr(), companyFromDB.getTelephoneNr());
        assertEquals(company.geteMail(), companyFromDB.geteMail());
        assertEquals(company.getWeb(), companyFromDB.getWeb());
        assertEquals(company.getEmployees(), companyFromDB.getEmployees());
        assertEquals(company.getAgreements(), companyFromDB.getAgreements());
    }
}
