package lv.velexauto.velex.mvc.Protected;

import com.google.gson.Gson;
import lv.velexauto.velex.HelperClasses.AgreementRequestBody;
import lv.velexauto.velex.HelperClasses.DataValidateAssistant;
import lv.velexauto.velex.HelperClasses.DateAssistant;
import lv.velexauto.velex.HelperClasses.SecurityAssistant;
import lv.velexauto.velex.database.AgreementDAO;
import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.database.UserDAO;
import lv.velexauto.velex.domain.Agreement;
import lv.velexauto.velex.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    @Qualifier("DataValidateAssistant")
    private DataValidateAssistant dataVA;

    @RequestMapping(value = {"protected/addagreement"}, method = RequestMethod.POST)
    public
    @ResponseBody
    String addAgreement(@RequestBody AgreementRequestBody agreementRB)
            throws ParseException, DBException {

        if (!dataVA.isAgreementRequestBodyValid(agreementRB)) {
            List<String> response = new ArrayList<String>();
            response.add("error");
            response.add("rejected");
            Gson gson = new Gson();
            return gson.toJson(response);
        }
        Agreement agreement = toAgreementDomain(agreementRB);
        agreementDAO.create(agreement);

        List<String> result = new ArrayList<String>();
        result.add("success");
        result.add("saved");
        Gson gson = new Gson();

        return gson.toJson(result);
    }

    /*private Employee getCurrentEmployee() throws DBException {

        String login = securityAssistant.getCurrentUserLogin();
        User user = userDAO.getByLogin(login);
        return user.getEmployee();
    }*/

    private Agreement toAgreementDomain(AgreementRequestBody agreementRB) throws ParseException, DBException {

        Employee employee = securityAssistant.getCurrentEmployee();
        System.out.println(employee.getName());
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
