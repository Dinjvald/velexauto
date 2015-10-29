package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.database.AgreementDAO;
import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.database.DatabaseCleaner;
import lv.velexauto.velex.domain.Agreement;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Dinjvald on 23/03/15.
 */

@WebAppConfiguration
public class AgreementDAOImplTest extends DAOImplTest {

    @Autowired
    @Qualifier("HibernateDAOAgreement")
    private AgreementDAO agreementDAO;

    private DatabaseCleaner databaseCleaner = new DatabaseCleaner();

    @Before
    public void init() throws DBException {
        databaseCleaner.cleanDatabase();
    }

    @Test
    @Transactional
    public void testCreateAndGetByIdAndGetAll() throws DBException {
        Agreement agreement = new Agreement("xxx111", "yyy111", "Kreiss", "01.01.2015", "Maskavas 250", "05.01.2015",
                "Kurzemes 102", 100.5, 10.7, "10-10-2015", "10-11-2015", "Cmr 001");
        Agreement agreement2 = new Agreement("111xxx", "111yyy", "Baltreiss", "31.12.2014", "Lacplesa 250",
                "10.01.2014", "Silenes 102", 2000.50, 1000.20, "11-10-2015", "11-11-2015", "Cmr002");
        agreementDAO.create(agreement);
        agreementDAO.create(agreement2);
        Agreement agreementFromDB = agreementDAO.getById(agreement.getAgreementId());
        List<Agreement> list = agreementDAO.getAll();
        assertNotNull(agreementFromDB);

        assertEquals(agreement.getAgreementId(), agreementFromDB.getAgreementId());
        assertEquals(agreement.getAgreementNr(), agreementFromDB.getAgreementNr());
        assertEquals(agreement.getLoadingAddress(), agreementFromDB.getLoadingAddress());
        assertEquals(agreement.getLoadingDate(), agreementFromDB.getLoadingDate());
        assertEquals(agreement.getUnloadingAddress(), agreementFromDB.getUnloadingAddress());
        assertEquals(agreement.getUnloadingDate(), agreementFromDB.getUnloadingDate());
        assertEquals(agreement.getPrice(), agreementFromDB.getPrice(), 1e-8);
        assertEquals(agreement.getValueAddedTax(), agreementFromDB.getValueAddedTax(), 1e-8);
        assertEquals(2, list.size());
    }

    @Test
    @Transactional
    public void testDeleteAndGetAll() throws DBException {
        Agreement agreement1 = new Agreement("xxx111", "yyy111", "Kreiss", "01.01.2015", "Maskavas 250", "05.01.2015",
                "Kurzemes 102", 100.5, 10.7, "10-10-2015", "10-11-2015", "Cmr 001");
        Agreement agreement2 = new Agreement("111xxx", "111yyy", "Baltreiss", "31.12.2014", "Lacplesa 250",
                "10.01.2014", "Silenes 102", 2000.50, 1000.20, "11-10-2015", "11-11-2015", "Cmr002");
        agreementDAO.create(agreement1);
        agreementDAO.create(agreement2);
        agreementDAO.delete(agreement1.getAgreementId());
        List<Agreement> list = agreementDAO.getAll();

        assertEquals(1, list.size());
    }

    @Test
    @Transactional
    public void testUpdateAndCreateAndGetById() throws DBException {
        Agreement agreement = new Agreement("xxx111", "yyy111", "Kreiss", "01.01.2015", "Maskavas 250", "05.01.2015",
                "Kurzemes 102", 100.5, 10.7, "10-10-2015", "10-11-2015", "Cmr 001");
        agreementDAO.create(agreement);
        Agreement agreementFromDB = agreementDAO.getById(agreement.getAgreementId());

        assertEquals(agreement.getAgreementId(), agreementFromDB.getAgreementId());

        agreement.setAgreementNr("111xxx");
        agreement.setLoadingDate("31.12.2014");
        agreement.setLoadingAddress("Lacplesa 250");
        agreement.setUnloadingDate("05.01.2015");
        agreement.setUnloadingAddress("Silenes 102");
        agreement.setPrice(2000.50);
        agreement.setValueAddedTax(1000.20);

        agreementDAO.update(agreement);
        Agreement updatedAgreementFromDB = agreementDAO.getById(agreement.getAgreementId());

        assertEquals(agreementFromDB.getAgreementId(), updatedAgreementFromDB.getAgreementId());
        assertEquals(agreement.getAgreementNr(), updatedAgreementFromDB.getAgreementNr());
        assertEquals(agreement.getLoadingDate(), updatedAgreementFromDB.getLoadingDate());
        assertEquals(agreement.getLoadingAddress(), updatedAgreementFromDB.getLoadingAddress());
        assertEquals(agreement.getUnloadingDate(), updatedAgreementFromDB.getUnloadingDate());
        assertEquals(agreement.getUnloadingAddress(), updatedAgreementFromDB.getUnloadingAddress());
        assertEquals(agreement.getPrice(), updatedAgreementFromDB.getPrice(), 1e-8);
        assertEquals(agreement.getValueAddedTax(), updatedAgreementFromDB.getValueAddedTax(), 1e-8);
    }

    @Test
    @Transactional
    public void testCreate() throws DBException {

        Agreement agreement = new Agreement.AgreementBuilder()
                .agreementNr("xxx111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate("20.10.2015")
                .loadingAddress("Maskavas 250")
                .unloadingDate("25.10.2015")
                .unloadingAddress("Kurzemes 102")
                .price(100.15)
                .valueAddedTax(100.23)
                .invoiceSendDate("30.10.2015")
                .estimatedDateOfPayment("30.11.2015")
                .onBehalfOf("Cmr 001")
                .build();

        agreementDAO.create(agreement);

        Agreement agreementFromDB = agreementDAO.getById(agreement.getAgreementId());

        assertEquals(agreement.getAgreementId(), agreementFromDB.getAgreementId());
        assertEquals(agreement.getAgreementNr(), agreementFromDB.getAgreementNr());
        assertEquals(agreement.getInvoiceNr(), agreementFromDB.getInvoiceNr());
        assertEquals(agreement.getClientName(), agreementFromDB.getClientName());
        assertEquals(agreement.getLoadingDate(), agreementFromDB.getLoadingDate());
        assertEquals(agreement.getLoadingAddress(), agreementFromDB.getLoadingAddress());
        assertEquals(agreement.getUnloadingDate(), agreementFromDB.getUnloadingDate());
        assertEquals(agreement.getUnloadingAddress(), agreementFromDB.getUnloadingAddress());
        assertEquals(agreement.getPrice(), agreementFromDB.getPrice(), 1e-8);
        assertEquals(agreement.getValueAddedTax(), agreementFromDB.getValueAddedTax(), 1e-8);
        assertEquals(agreement.getInvoiceSendDate(), agreementFromDB.getInvoiceSendDate());
        assertEquals(agreement.getEstimatedDateOfPayment(), agreementFromDB.getEstimatedDateOfPayment());
        assertEquals(agreement.getOnBehalfOf(), agreementFromDB.getOnBehalfOf());
    }

    @Test
    @Transactional
    public void testUpdate() throws DBException {

        Agreement agreement = new Agreement.AgreementBuilder()
                .agreementNr("xxx111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate("20.10.2015")
                .loadingAddress("Maskavas 250")
                .unloadingDate("25.10.2015")
                .unloadingAddress("Kurzemes 102")
                .price(100.15)
                .valueAddedTax(100.23)
                .invoiceSendDate("30.10.2015")
                .estimatedDateOfPayment("30.11.2015")
                .onBehalfOf("Cmr 001")
                .build();

        agreementDAO.create(agreement);

        Agreement agreementFromDB = agreementDAO.getById(agreement.getAgreementId());

        assertEquals(agreement.getAgreementId(), agreementFromDB.getAgreementId());

        agreementFromDB.setAgreementNr("yyy222");
        agreementFromDB.setInvoiceNr("1015/55");
        agreementFromDB.setClientName("Baltreiss");
        agreementFromDB.setLoadingDate("21.11.2015");
        agreementFromDB.setLoadingAddress("Kurzemes pr. 102");
        agreementFromDB.setUnloadingDate("26.11.2015");
        agreementFromDB.setUnloadingAddress("Maskavas 250");
        agreementFromDB.setPrice(99.99);
        agreementFromDB.setValueAddedTax(20.59);
        agreementFromDB.setInvoiceSendDate("30.11.2015");
        agreementFromDB.setEstimatedDateOfPayment("30.12.2015");
        agreementFromDB.setOnBehalfOf("Cmr 010");

        agreementDAO.update(agreementFromDB);

        agreement = agreementDAO.getById(agreementFromDB.getAgreementId());

        assertEquals(agreement.getAgreementId(), agreementFromDB.getAgreementId());
        assertEquals(agreement.getAgreementNr(), agreementFromDB.getAgreementNr());
        assertEquals(agreement.getInvoiceNr(), agreementFromDB.getInvoiceNr());
        assertEquals(agreement.getClientName(), agreementFromDB.getClientName());
        assertEquals(agreement.getLoadingDate(), agreementFromDB.getLoadingDate());
        assertEquals(agreement.getLoadingAddress(), agreementFromDB.getLoadingAddress());
        assertEquals(agreement.getUnloadingDate(), agreementFromDB.getUnloadingDate());
        assertEquals(agreement.getUnloadingAddress(), agreementFromDB.getUnloadingAddress());
        assertEquals(agreement.getPrice(), agreementFromDB.getPrice(), 1e-8);
        assertEquals(agreement.getValueAddedTax(), agreementFromDB.getValueAddedTax(), 1e-8);
        assertEquals(agreement.getInvoiceSendDate(), agreementFromDB.getInvoiceSendDate());
        assertEquals(agreement.getEstimatedDateOfPayment(), agreementFromDB.getEstimatedDateOfPayment());
        assertEquals(agreement.getOnBehalfOf(), agreementFromDB.getOnBehalfOf());
    }

    @Test
    @Transactional
    public void testDelete() throws DBException {

        Agreement agreement1 = new Agreement.AgreementBuilder()
                .agreementNr("xxx111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate("20.10.2015")
                .loadingAddress("Maskavas 250")
                .unloadingDate("25.10.2015")
                .unloadingAddress("Kurzemes 102")
                .price(100.15)
                .valueAddedTax(100.23)
                .invoiceSendDate("30.10.2015")
                .estimatedDateOfPayment("30.11.2015")
                .onBehalfOf("Cmr 001")
                .build();
        agreementDAO.create(agreement1);

        Agreement agreement2 = new Agreement.AgreementBuilder()
                .agreementNr("111xxx")
                .invoiceNr("1015/55")
                .clientName("Baltreiss")
                .loadingDate("21.10.2015")
                .loadingAddress("Kurzemes 102")
                .unloadingDate("26.10.2015")
                .unloadingAddress("Maskavas 250")
                .price(99.15)
                .valueAddedTax(20.23)
                .invoiceSendDate("30.11.2015")
                .estimatedDateOfPayment("30.12.2015")
                .onBehalfOf("Cmr 010")
                .build();
        agreementDAO.create(agreement2);

        List <Agreement> list = agreementDAO.getAll();

        assertEquals(2, list.size());

        agreementDAO.delete(agreement2.getAgreementId());

        list = agreementDAO.getAll();

        assertEquals(1, list.size());
    }

    @Test
    @Transactional
    public void testGetAll() throws DBException {

        Agreement agreement1 = new Agreement.AgreementBuilder()
                .agreementNr("xxx111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate("20.10.2015")
                .loadingAddress("Maskavas 250")
                .unloadingDate("25.10.2015")
                .unloadingAddress("Kurzemes 102")
                .price(100.15)
                .valueAddedTax(100.23)
                .invoiceSendDate("30.10.2015")
                .estimatedDateOfPayment("30.11.2015")
                .onBehalfOf("Cmr 001")
                .build();
        agreementDAO.create(agreement1);

        Agreement agreement2 = new Agreement.AgreementBuilder()
                .agreementNr("yyy111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate("20.10.2015")
                .loadingAddress("Maskavas 250")
                .unloadingDate("25.10.2015")
                .unloadingAddress("Kurzemes 102")
                .price(100.15)
                .valueAddedTax(100.23)
                .invoiceSendDate("30.10.2015")
                .estimatedDateOfPayment("30.11.2015")
                .onBehalfOf("Cmr 001")
                .build();
        agreementDAO.create(agreement2);

        Agreement agreement3 = new Agreement.AgreementBuilder()
                .agreementNr("zzz111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate("20.10.2015")
                .loadingAddress("Maskavas 250")
                .unloadingDate("25.10.2015")
                .unloadingAddress("Kurzemes 102")
                .price(100.15)
                .valueAddedTax(100.23)
                .invoiceSendDate("30.10.2015")
                .estimatedDateOfPayment("30.11.2015")
                .onBehalfOf("Cmr 001")
                .build();
        agreementDAO.create(agreement3);

        List <Agreement> list = agreementDAO.getAll();

        assertEquals(3, list.size());
    }
}
