package lv.velexauto.velex.database.hibernate;

import lv.velexauto.velex.HelperClasses.DateAssistant;
import lv.velexauto.velex.database.*;
import lv.velexauto.velex.domain.Agreement;
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

        Agreement agreement = new Agreement.AgreementBuilder()
                .agreementNr("xxx111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate(dateAssistant.stringToDate("20.10.2015"))
                .loadingAddress("Maskavas 250")
                .unloadingDate(dateAssistant.stringToDate("25.10.2015"))
                .unloadingAddress("Kurzemes 102")
                .price(100.15)
                .valueAddedTax(100.23)
                .paymentTerm(30)
                .invoiceSendDate(dateAssistant.stringToDate("30.10.2015"))
                .estimatedDateOfPayment(dateAssistant.stringToDate("30.11.2015"))
                .onBehalfOf("Cmr 001")
                .fileLinkAgreement("http//www/velexauto.lv")
                .fileLinkInvoice("http://www.kreiss.lv")
                .notes("no notes")
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
        assertEquals(agreement.getPaymentTerm(), agreementFromDB.getPaymentTerm());
        assertEquals(agreement.getValueAddedTax(), agreementFromDB.getValueAddedTax(), 1e-8);
        assertEquals(agreement.getInvoiceSendDate(), agreementFromDB.getInvoiceSendDate());
        assertEquals(agreement.getEstimatedDateOfPayment(), agreementFromDB.getEstimatedDateOfPayment());
        assertEquals(agreement.getOnBehalfOf(), agreementFromDB.getOnBehalfOf());
        assertEquals(agreement.getFileLinkAgreement(), agreementFromDB.getFileLinkAgreement());
        assertEquals(agreement.getFileLinkInvoice(), agreementFromDB.getFileLinkInvoice());
        assertEquals(agreement.getNotes(), agreementFromDB.getNotes());
    }

    @Test
    @Transactional
    public void testUpdate() throws DBException, ParseException {

        Agreement agreement = new Agreement.AgreementBuilder()
                .agreementNr("xxx111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate(dateAssistant.stringToDate("20.10.2015"))
                .loadingAddress("Maskavas 250")
                .unloadingDate(dateAssistant.stringToDate("25.10.2015"))
                .unloadingAddress("Kurzemes 102")
                .price(100.15)
                .valueAddedTax(100.23)
                .paymentTerm(30)
                .invoiceSendDate(dateAssistant.stringToDate("30.10.2015"))
                .estimatedDateOfPayment(dateAssistant.stringToDate("30.11.2015"))
                .onBehalfOf("Cmr 001")
                .fileLinkAgreement("http//www/velexauto.lv")
                .fileLinkInvoice("http://www.kreiss.lv")
                .notes("no notes")
                .build();

        agreementDAO.create(agreement);

        Agreement agreementFromDB = agreementDAO.getById(agreement.getAgreementId());

        assertEquals(agreement.getAgreementId(), agreementFromDB.getAgreementId());

        agreementFromDB.setAgreementNr("yyy222");
        agreementFromDB.setInvoiceNr("1015/55");
        agreementFromDB.setClientName("Baltreiss");
        agreementFromDB.setLoadingDate(dateAssistant.stringToDate("21.11.2015"));
        agreementFromDB.setLoadingAddress("Kurzemes pr. 102");
        agreementFromDB.setUnloadingDate(dateAssistant.stringToDate("26.11.2015"));
        agreementFromDB.setUnloadingAddress("Maskavas 250");
        agreementFromDB.setPrice(99.99);
        agreementFromDB.setValueAddedTax(20.59);
        agreementFromDB.setPaymentTerm(15);
        agreementFromDB.setInvoiceSendDate(dateAssistant.stringToDate("30.11.2015"));
        agreementFromDB.setEstimatedDateOfPayment(dateAssistant.stringToDate("30.12.2015"));
        agreementFromDB.setOnBehalfOf("Cmr 010");
        agreementFromDB.setFileLinkAgreement("http//www/velexauto.ru");
        agreementFromDB.setFileLinkInvoice("http://www.kreiss.ru");
        agreementFromDB.setNotes("some note");

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
        assertEquals(agreement.getPaymentTerm(), agreementFromDB.getPaymentTerm());
        assertEquals(agreement.getValueAddedTax(), agreementFromDB.getValueAddedTax(), 1e-8);
        assertEquals(agreement.getInvoiceSendDate(), agreementFromDB.getInvoiceSendDate());
        assertEquals(agreement.getEstimatedDateOfPayment(), agreementFromDB.getEstimatedDateOfPayment());
        assertEquals(agreement.getOnBehalfOf(), agreementFromDB.getOnBehalfOf());
        assertEquals(agreement.getFileLinkAgreement(), agreementFromDB.getFileLinkAgreement());
        assertEquals(agreement.getFileLinkInvoice(), agreementFromDB.getFileLinkInvoice());
        assertEquals(agreement.getNotes(), agreementFromDB.getNotes());
    }

    @Test
    @Transactional
    public void testDelete() throws DBException, ParseException {

        Agreement agreement1 = new Agreement.AgreementBuilder()
                .agreementNr("xxx111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate(dateAssistant.stringToDate("20.10.2015"))
                .loadingAddress("Maskavas 250")
                .unloadingDate(dateAssistant.stringToDate("25.10.2015"))
                .unloadingAddress("Kurzemes 102")
                .price(100.15)
                .valueAddedTax(100.23)
                .paymentTerm(30)
                .invoiceSendDate(dateAssistant.stringToDate("30.10.2015"))
                .estimatedDateOfPayment(dateAssistant.stringToDate("30.11.2015"))
                .onBehalfOf("Cmr 001")
                .fileLinkAgreement("http//www/velexauto.lv")
                .fileLinkInvoice("http://www.kreiss.lv")
                .notes("one notes")
                .build();
        agreementDAO.create(agreement1);

        Agreement agreement2 = new Agreement.AgreementBuilder()
                .agreementNr("111xxx")
                .invoiceNr("1015/55")
                .clientName("Baltreiss")
                .loadingDate(dateAssistant.stringToDate("21.10.2015"))
                .loadingAddress("Kurzemes 102")
                .unloadingDate(dateAssistant.stringToDate("26.10.2015"))
                .unloadingAddress("Maskavas 250")
                .price(99.15)
                .valueAddedTax(20.23)
                .paymentTerm(15)
                .invoiceSendDate(dateAssistant.stringToDate("30.11.2015"))
                .estimatedDateOfPayment(dateAssistant.stringToDate("30.12.2015"))
                .onBehalfOf("Cmr 010")
                .fileLinkAgreement("http//www/bilatris.lv")
                .fileLinkInvoice("http://www.baltreiss.lv")
                .notes("two notes")
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
    public void testGetAll() throws DBException, ParseException {

        Agreement agreement1 = new Agreement.AgreementBuilder()
                .agreementNr("xxx111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate(dateAssistant.stringToDate("20.10.2015"))
                .loadingAddress("Maskavas 250")
                .unloadingDate(dateAssistant.stringToDate("25.10.2015"))
                .unloadingAddress("Kurzemes 102")
                .price(100.15)
                .valueAddedTax(100.23)
                .paymentTerm(30)
                .invoiceSendDate(dateAssistant.stringToDate("30.10.2015"))
                .estimatedDateOfPayment(dateAssistant.stringToDate("30.11.2015"))
                .onBehalfOf("Cmr 001")
                .fileLinkAgreement("http//www/velexauto.lv")
                .fileLinkInvoice("http://www.kreiss.lv")
                .notes("no notes")
                .build();
        agreementDAO.create(agreement1);

        Agreement agreement2 = new Agreement.AgreementBuilder()
                .agreementNr("xxx111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate(dateAssistant.stringToDate("20.10.2015"))
                .loadingAddress("Maskavas 250")
                .unloadingDate(dateAssistant.stringToDate("25.10.2015"))
                .unloadingAddress("Kurzemes 102")
                .price(100.15)
                .valueAddedTax(100.23)
                .paymentTerm(30)
                .invoiceSendDate(dateAssistant.stringToDate("30.10.2015"))
                .estimatedDateOfPayment(dateAssistant.stringToDate("30.11.2015"))
                .onBehalfOf("Cmr 001")
                .fileLinkAgreement("http//www/velexauto.lv")
                .fileLinkInvoice("http://www.kreiss.lv")
                .notes("no notes")
                .build();
        agreementDAO.create(agreement2);

        Agreement agreement3 = new Agreement.AgreementBuilder()
                .agreementNr("xxx111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate(dateAssistant.stringToDate("20.10.2015"))
                .loadingAddress("Maskavas 250")
                .unloadingDate(dateAssistant.stringToDate("25.10.2015"))
                .unloadingAddress("Kurzemes 102")
                .price(100.15)
                .valueAddedTax(100.23)
                .paymentTerm(30)
                .invoiceSendDate(dateAssistant.stringToDate("30.10.2015"))
                .estimatedDateOfPayment(dateAssistant.stringToDate("30.11.2015"))
                .onBehalfOf("Cmr 001")
                .fileLinkAgreement("http//www/velexauto.lv")
                .fileLinkInvoice("http://www.kreiss.lv")
                .notes("no notes")
                .build();
        agreementDAO.create(agreement3);

        List <Agreement> list = agreementDAO.getAll();

        assertEquals(3, list.size());
    }
}
