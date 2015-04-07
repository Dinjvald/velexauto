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
    public void testCreateAndGetByIdAndGetAll() throws DBException{
        Agreement agreement = new Agreement("xxx111", "01.01.2015", "Maskavas 250", "05.01.2015", "Kurzemes 102", 100.5, 10.7);
        Agreement agreement2 = new Agreement("111xxx", "31.12.2014", "Lacplesa 250", "10.01.2014", "Silenes 102", 2000.50, 1000.20);
        agreementDAO.create(agreement);
        agreementDAO.create(agreement2);
        Agreement agreementFromDB = agreementDAO.getById(agreement.getAgreementId());
        List <Agreement> list = agreementDAO.getAll();
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
        Agreement agreement1 = new Agreement("xxx111", "01.01.2015", "Maskavas 250", "05.01.2015", "Kurzemes 102", 100.5, 10.7);
        Agreement agreement2 = new Agreement("111xxx", "31.12.2014", "Lacplesa 250", "10.01.2014", "Silenes 102", 2000.50, 1000.20);
        agreementDAO.create(agreement1);
        agreementDAO.create(agreement2);
        agreementDAO.delete(agreement1.getAgreementId());
        List<Agreement> list = agreementDAO.getAll();

        assertEquals(1, list.size());
    }

    @Test
    @Transactional
    public void testUpdateAndCreateAndGetById() throws DBException {
        Agreement agreement = new Agreement("xxx111", "01.01.2015", "Maskavas 250", "05.01.2015", "Kurzemes 102", 100.5, 10.7);
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

}
