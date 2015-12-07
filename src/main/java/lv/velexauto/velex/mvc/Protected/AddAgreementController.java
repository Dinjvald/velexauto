package lv.velexauto.velex.mvc.Protected;

import lv.velexauto.velex.HelperClasses.AgreementRequestBody;
import lv.velexauto.velex.HelperClasses.DateAssistant;
import lv.velexauto.velex.HelperClasses.SecurityAssistant;
import lv.velexauto.velex.database.AgreementDAO;
import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.database.UserDAO;
import lv.velexauto.velex.domain.Agreement;
import lv.velexauto.velex.domain.Employee;
import lv.velexauto.velex.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;

/**
 * Created by Dinjvald on 03/12/15.
 */
@Controller
public class AddAgreementController {

    @Autowired
    @Qualifier("SecurityAssistant")
    private SecurityAssistant securityAssistant;

    @Autowired
    @Qualifier("HibernateDAOUser")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("HibernateDAOAgreement")
    private AgreementDAO agreementDAO;

    @Autowired
    @Qualifier("DateAssistant")
    private DateAssistant dateAssistant;

    @RequestMapping(value = {"protected/addagreement"}, method = RequestMethod.POST)
    public @ResponseBody String addAgreement(@RequestBody AgreementRequestBody agreementRB)
            throws ParseException, DBException {

        System.out.println(agreementRB);

        Agreement agreement = toAgreementDomain(agreementRB);
        agreementDAO.create(agreement);
        Agreement agreementFromDB = agreementDAO.getById(agreement.getAgreementId());
        System.out.println(agreementFromDB.getLoadingAddress());
        System.out.println(agreementFromDB.getUnloadingAddress());
        return "Success";
    }

    private Employee getCurrentEmployee() throws DBException {

        String login = securityAssistant.getCurrentUserLogin();
        User user = userDAO.getByLogin(login);
        return user.getEmployee();
    }

    public Agreement toAgreementDomain(AgreementRequestBody agreementRB) throws ParseException, DBException {

        Employee employee = getCurrentEmployee();
        Agreement agreement = new Agreement();

        agreement.setEmployee(employee);
        agreement.setCompany(employee.getCompany());
        agreement.setAgreementNr(agreementRB.getAgreementNr());
        agreement.setInvoiceNr(agreementRB.getInvoiceNr());
        agreement.setClientName(agreementRB.getClientName());
        agreement.setLoadingDate(dateAssistant.stringToDate(agreementRB.getLoadingDate()));
        agreement.setLoadingAddress(agreementRB.getLoadingAddress());
        agreement.setUnloadingDate(dateAssistant.stringToDate(agreementRB.getUnloadingDate()));
        agreement.setUnloadingAddress(agreementRB.getUnloadingAddress());
        agreement.setDriver(agreementRB.getDriver());
        agreement.setPlateNr(agreementRB.getPlateNr());
        agreement.setPrice(agreementRB.getPrice());
        agreement.setValueAddedTax(agreementRB.getValueAddedTax());
        agreement.setPaymentTerm(agreementRB.getPaymentTerm());
        agreement.setInvoiceSendDate(dateAssistant.stringToDate(agreementRB.getInvoiceSendDate()));
        agreement.setOnBehalfOf(agreementRB.getOnBehalfOf());
        agreement.setFileLinkAgreement(agreementRB.getFileLinkAgreement());
        agreement.setFileLinkInvoice(agreementRB.getFileLinkInvoice());
        agreement.setNotes(agreementRB.getNotes());

        return agreement;
    }
}
