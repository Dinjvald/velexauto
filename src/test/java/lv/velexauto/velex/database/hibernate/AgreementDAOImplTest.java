package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.HelperClasses.DateAssistant;
import lv.velexauto.velex.database.*;
import lv.velexauto.velex.domain.Agreement;
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
 * Created by Dinjvald on 23/03/15.
 * */



@WebAppConfiguration
public class AgreementDAOImplTest extends DAOImplTest {

    @Autowired
    @Qualifier("HibernateDAOCompany")
    private CompanyDAO companyDAO;

    @Autowired
    @Qualifier("HibernateDAOEmployee")
    private EmployeeDAO employeeDAO;

    @Autowired
    @Qualifier("HibernateDAOAgreement")
    private AgreementDAO agreementDAO;

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
        Agreement agreement = createFirstAgreement(employee, company);
        agreementDAO.create(agreement);

        Agreement agreementFromDB = agreementDAO.getById(agreement.getAgreementId());

        testWithAssertEquals(agreement, agreementFromDB);
    }

    @Test
    @Transactional
    public void testUpdate() throws DBException, ParseException {

        Company company1 = createFirstCompany();
        companyDAO.create(company1);
        Company company2 = createSecondCompany();
        companyDAO.create(company2);

        Employee employee1 = createFirstEmployee(company1);
        employeeDAO.create(employee1);
        Employee employee2 = createSecondEmployee(company2);
        employeeDAO.create(employee2);

        Agreement agreement = createFirstAgreement(employee1, company1);
        agreementDAO.create(agreement);

        Agreement agreementFromDB = agreementDAO.getById(agreement.getAgreementId());

        assertEquals(agreement.getAgreementId(), agreementFromDB.getAgreementId());

        agreement = updateAgreementWithNewData(agreement, company2, employee2);
        agreementDAO.update(agreement);

        agreementFromDB = agreementDAO.getById(agreement.getAgreementId());

        testWithAssertEquals(agreement, agreementFromDB);
    }

    @Test
    @Transactional
    public void testDelete() throws DBException, ParseException {

        Company company = createFirstCompany();
        companyDAO.create(company);
        Employee employee = createFirstEmployee(company);
        employeeDAO.create(employee);
        Agreement agreement1 = createFirstAgreement(employee, company);
        agreementDAO.create(agreement1);
        Agreement agreement2 = createSecondAgreement(employee, company);
        agreementDAO.create(agreement2);

        List <Agreement> list = agreementDAO.getAll();

        assertEquals(2, list.size());

        agreementDAO.delete(agreement1.getAgreementId());

        list = agreementDAO.getAll();

        assertEquals(1, list.size());
    }

    @Test
    @Transactional
    public void testGetAll() throws DBException, ParseException {

        Company company = createFirstCompany();
        companyDAO.create(company);
        Employee employee = createFirstEmployee(company);
        employeeDAO.create(employee);

        Agreement agreement1 = createFirstAgreement(employee, company);
        agreementDAO.create(agreement1);
        Agreement agreement2 = createSecondAgreement(employee, company);
        agreementDAO.create(agreement2);
        Agreement agreement3 = createThirdAgreement(employee, company);
        agreementDAO.create(agreement3);

        List <Agreement> list = agreementDAO.getAll();

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

    private Agreement createFirstAgreement(Employee employee, Company company) throws ParseException {

        return new Agreement.AgreementBuilder()
                .company(company)
                .employee(employee)
                .agreementNr("xxx111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate(dateAssistant.stringToDate("20.10.2015"))
                .loadingAddress("Maskavas 250")
                .unloadingDate(dateAssistant.stringToDate("25.10.2015"))
                .unloadingAddress("Kurzemes 102")
                .driver("Vladimirs Beskorovainijs")
                .plateNr("KE 2300 / T 4641")
                .price(100.15)
                .valueAddedTax(100.23)
                .paymentTerm(30)
                .invoiceSendDate(dateAssistant.stringToDate("30.10.2015"))
                .estimatedDateOfPayment(dateAssistant.stringToDate("30.11.2015"))
                .onBehalfOf("Cmr 001")
                .fileLinkAgreement("http//www/velexauto.lv")
                .fileLinkInvoice("http://www.kreiss.lv")
                .notes("no notes")
                .isPaid(false)
                .build();
    }

    private Agreement createSecondAgreement(Employee employee, Company company) throws ParseException {

        return new Agreement.AgreementBuilder()
                .company(company)
                .employee(employee)
                .agreementNr("111xxx")
                .invoiceNr("1015/55")
                .clientName("Baltreiss")
                .loadingDate(dateAssistant.stringToDate("21.10.2015"))
                .loadingAddress("Kurzemes 102")
                .unloadingDate(dateAssistant.stringToDate("26.10.2015"))
                .unloadingAddress("Maskavas 250")
                .driver("Rublev")
                .plateNr("KR 8759 / D 4598")
                .price(99.15)
                .valueAddedTax(20.23)
                .paymentTerm(15)
                .invoiceSendDate(dateAssistant.stringToDate("30.11.2015"))
                .estimatedDateOfPayment(dateAssistant.stringToDate("30.12.2015"))
                .onBehalfOf("Cmr 010")
                .fileLinkAgreement("http//www/bilatris.lv")
                .fileLinkInvoice("http://www.baltreiss.lv")
                .notes("two notes")
                .isPaid(false)
                .build();
    }

    private Agreement createThirdAgreement(Employee employee, Company company) throws ParseException {

        return new Agreement.AgreementBuilder()
                .company(company)
                .employee(employee)
                .agreementNr("xxx111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate(dateAssistant.stringToDate("20.10.2015"))
                .loadingAddress("Maskavas 250")
                .unloadingDate(dateAssistant.stringToDate("25.10.2015"))
                .unloadingAddress("Kurzemes 102")
                .driver("Ruslan")
                .plateNr("JT 5698 / K 2635")
                .price(100.15)
                .valueAddedTax(100.23)
                .paymentTerm(30)
                .invoiceSendDate(dateAssistant.stringToDate("30.10.2015"))
                .estimatedDateOfPayment(dateAssistant.stringToDate("30.11.2015"))
                .onBehalfOf("Cmr 001")
                .fileLinkAgreement("http//www/velexauto.lv")
                .fileLinkInvoice("http://www.kreiss.lv")
                .notes("no notes")
                .isPaid(false)
                .build();
    }

    private void testWithAssertEquals(Agreement agreement, Agreement agreementFromDB) {

        assertEquals(agreement.getAgreementId(), agreementFromDB.getAgreementId());
        assertEquals(agreement.getCompany(), agreementFromDB.getCompany());
        assertEquals(agreement.getEmployee(), agreementFromDB.getEmployee());
        assertEquals(agreement.getAgreementNr(), agreementFromDB.getAgreementNr());
        assertEquals(agreement.getInvoiceNr(), agreementFromDB.getInvoiceNr());
        assertEquals(agreement.getClientName(), agreementFromDB.getClientName());
        assertEquals(agreement.getLoadingDate(), agreementFromDB.getLoadingDate());
        assertEquals(agreement.getLoadingAddress(), agreementFromDB.getLoadingAddress());
        assertEquals(agreement.getUnloadingDate(), agreementFromDB.getUnloadingDate());
        assertEquals(agreement.getUnloadingAddress(), agreementFromDB.getUnloadingAddress());
        assertEquals(agreement.getDriver(), agreementFromDB.getDriver());
        assertEquals(agreement.getPlateNr(), agreementFromDB.getPlateNr());
        assertEquals(agreement.getPrice(), agreementFromDB.getPrice(), 1e-8);
        assertEquals(agreement.getPaymentTerm(), agreementFromDB.getPaymentTerm());
        assertEquals(agreement.getValueAddedTax(), agreementFromDB.getValueAddedTax(), 1e-8);
        assertEquals(agreement.getInvoiceSendDate(), agreementFromDB.getInvoiceSendDate());
        assertEquals(agreement.getEstimatedDateOfPayment(), agreementFromDB.getEstimatedDateOfPayment());
        assertEquals(agreement.getOnBehalfOf(), agreementFromDB.getOnBehalfOf());
        assertEquals(agreement.getFileLinkAgreement(), agreementFromDB.getFileLinkAgreement());
        assertEquals(agreement.getFileLinkInvoice(), agreementFromDB.getFileLinkInvoice());
        assertEquals(agreement.getNotes(), agreementFromDB.getNotes());
        assertEquals(agreement.isPaid(), agreementFromDB.isPaid());
    }

    private Agreement updateAgreementWithNewData(Agreement agreement, Company company, Employee employee) throws ParseException {

        agreement.setCompany(company);
        agreement.setEmployee(employee);
        agreement.setAgreementNr("yyy222");
        agreement.setInvoiceNr("1015/55");
        agreement.setClientName("Baltreiss");
        agreement.setLoadingDate(dateAssistant.stringToDate("21.11.2015"));
        agreement.setLoadingAddress("Kurzemes pr. 102");
        agreement.setUnloadingDate(dateAssistant.stringToDate("26.11.2015"));
        agreement.setUnloadingAddress("Maskavas 250");
        agreement.setDriver("Rublev Vladimir");
        agreement.setPlateNr("TJ 7525 / K 9569");
        agreement.setPrice(99.99);
        agreement.setValueAddedTax(20.59);
        agreement.setPaymentTerm(15);
        agreement.setInvoiceSendDate(dateAssistant.stringToDate("30.11.2015"));
        agreement.setEstimatedDateOfPayment(dateAssistant.stringToDate("30.12.2015"));
        agreement.setOnBehalfOf("Cmr 010");
        agreement.setFileLinkAgreement("http//www/velexauto.ru");
        agreement.setFileLinkInvoice("http://www.kreiss.ru");
        agreement.setNotes("some note");
        agreement.setPaid(true);
        return agreement;
    }
}
