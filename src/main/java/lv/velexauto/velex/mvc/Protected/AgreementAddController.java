package lv.velexauto.velex.mvc.Protected;

import com.google.gson.Gson;
import lv.velexauto.velex.HelperClasses.AgreementRequestBody;
import lv.velexauto.velex.HelperClasses.DataValidateAssistant;
import lv.velexauto.velex.HelperClasses.DateAssistant;
import lv.velexauto.velex.HelperClasses.SecurityAssistant;
import lv.velexauto.velex.database.AgreementDAO;
import lv.velexauto.velex.database.DBException;
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
public class AgreementAddController {

    private final String AGREEMENT = "agreement";

    @Autowired
    @Qualifier("HibernateDAOAgreement")
    private AgreementDAO agreementDAO;

    @Autowired
    @Qualifier("SecurityAssistant")
    private SecurityAssistant securityAssistant;

    @Autowired
    @Qualifier("DateAssistant")
    private DateAssistant dateAssistant;

    @Autowired
    @Qualifier("DataValidateAssistant")
    private DataValidateAssistant dataValidateAssistant;

    @RequestMapping(value = {"protected/addagreement"}, method = RequestMethod.POST)
    public
    @ResponseBody
    String addAgreement(@RequestBody AgreementRequestBody agreementRB)
            throws ParseException, DBException {

        if (!dataValidateAssistant.isAgreementRequestBodyValid(agreementRB)) {
            return alertError();
        }
        Agreement agreement = toAgreementDomain(agreementRB);
        agreementDAO.create(agreement);

        return alertSuccess();
    }

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

        java.util.Date paymentDate = calculatePaymentDate(agreement.getInvoiceSendDate(), agreement.getPaymentTerm());
        agreement.setEstimatedDateOfPayment(paymentDate);

        return agreement;
    }

    private java.util.Date calculatePaymentDate(java.util.Date date, int paymentTerm) throws ParseException {
        java.util.Date def = dateAssistant.stringToDate(DataValidateAssistant.DEFAULT_DATE);
        if (date == def || paymentTerm == DataValidateAssistant.DEFAULT_INT) return def;
        return dateAssistant.addDaysToDate(date, paymentTerm);
    }

    private String alertSuccess() {
        List<String> response = new ArrayList<>();
        response.add(DataValidateAssistant.SUCCESS);
        response.add(AGREEMENT);
        Gson gson = new Gson();
        return gson.toJson(response);
    }

    private String alertError() {
        List<String> response = new ArrayList<>();
        response.add(DataValidateAssistant.ERROR);
        response.add(AGREEMENT);
        Gson gson = new Gson();
        return gson.toJson(response);
    }
}
