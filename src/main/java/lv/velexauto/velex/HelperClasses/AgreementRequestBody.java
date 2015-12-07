package lv.velexauto.velex.HelperClasses;

import lv.velexauto.velex.database.DBException;
import lv.velexauto.velex.database.UserDAO;
import lv.velexauto.velex.domain.Agreement;
import lv.velexauto.velex.domain.Company;
import lv.velexauto.velex.domain.Employee;
import lv.velexauto.velex.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * Created by Dinjvald on 07/12/15.
 */
public class AgreementRequestBody {

    @Autowired
    @Qualifier("DateAssistant")
    private DateAssistant dateAssistant;

    @Autowired
    @Qualifier("SecurityAssistant")
    private SecurityAssistant securityAssistant;

    @Autowired
    @Qualifier("HibernateDAOUser")
    private UserDAO userDAO;

    String loadingDate;
    String loadingAddress;
    String unloadingDate;
    String unloadingAddress;
    String clientName;
    String agreementNr;
    String invoiceNr;
    String onBehalfOf;
    String fileLinkAgreement;
    String fileLinkInvoice;
    int price;
    double valueAddedTax;
    int paymentTerm;
    String driver;
    String plateNr;
    String invoiceSendDate;
    String notes;

    public String getLoadingDate() {
        return loadingDate;
    }

    public String getLoadingAddress() {
        return loadingAddress;
    }

    public String getUnloadingDate() {
        return unloadingDate;
    }

    public String getUnloadingAddress() {
        return unloadingAddress;
    }

    public String getClientName() {
        return clientName;
    }

    public String getAgreementNr() {
        return agreementNr;
    }

    public String getInvoiceNr() {
        return invoiceNr;
    }

    public String getOnBehalfOf() {
        return onBehalfOf;
    }

    public String getFileLinkAgreement() {
        return fileLinkAgreement;
    }

    public String getFileLinkInvoice() {
        return fileLinkInvoice;
    }

    public int getPrice() {
        return price;
    }

    public double getValueAddedTax() {
        return valueAddedTax;
    }

    public int getPaymentTerm() {
        return paymentTerm;
    }

    public String getDriver() {
        return driver;
    }

    public String getPlateNr() {
        return plateNr;
    }

    public String getInvoiceSendDate() {
        return invoiceSendDate;
    }

    public String getNotes() {
        return notes;
    }

    public Agreement toAgreementDomain() throws ParseException, DBException {

        /*Employee employee = getCurrentEmployee();
        Company company = employee.getCompany();*/

        System.out.println(dateAssistant);
        System.out.println(securityAssistant);
        System.out.println(userDAO);

        Agreement agreement = new Agreement();
        agreement.setAgreementNr(this.agreementNr);
        agreement.setInvoiceNr(this.invoiceNr);
        agreement.setClientName(this.clientName);
        agreement.setLoadingDate(dateAssistant.stringToDate(this.loadingDate));
        agreement.setLoadingAddress(this.loadingAddress);
        agreement.setUnloadingDate(dateAssistant.stringToDate(this.unloadingDate));
        agreement.setUnloadingAddress(this.unloadingAddress);
        agreement.setDriver(this.driver);
        agreement.setPlateNr(this.plateNr);
        agreement.setPrice(this.price);
        agreement.setValueAddedTax(this.valueAddedTax);
        agreement.setPaymentTerm(this.paymentTerm);
        agreement.setInvoiceSendDate(dateAssistant.stringToDate(this.invoiceSendDate));
        agreement.setOnBehalfOf(this.onBehalfOf);
        agreement.setFileLinkAgreement(this.fileLinkAgreement);
        agreement.setFileLinkInvoice(this.fileLinkInvoice);
        agreement.setNotes(this.notes);
        /*agreement.setEmployee(employee);
        agreement.setCompany(company);*/
        return agreement;
    }

    @Override
    public String toString() {
        return "AgreementRequestBody{" +
                "loadingDate='" + loadingDate + '\'' +
                ", loadingAddress='" + loadingAddress + '\'' +
                ", unloadingDate='" + unloadingDate + '\'' +
                ", unloadingAddress='" + unloadingAddress + '\'' +
                ", clientName='" + clientName + '\'' +
                ", agreementNr='" + agreementNr + '\'' +
                ", invoiceNr='" + invoiceNr + '\'' +
                ", onBehalfOf='" + onBehalfOf + '\'' +
                ", fileLinkAgreement='" + fileLinkAgreement + '\'' +
                ", fileLinkInvoice='" + fileLinkInvoice + '\'' +
                ", price=" + price +
                ", valueAddedTax=" + valueAddedTax +
                ", paymentTerm=" + paymentTerm +
                ", driver='" + driver + '\'' +
                ", plateNr='" + plateNr + '\'' +
                ", invoiceSendDate='" + invoiceSendDate + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    /*private Employee getCurrentEmployee() throws DBException {
        String login = securityAssistant.getCurrentUserLogin();
        User user = userDAO.getByLogin(login);
        return user.getEmployee();
    }*/
}
