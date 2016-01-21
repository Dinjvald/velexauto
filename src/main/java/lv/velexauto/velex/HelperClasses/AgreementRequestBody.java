package lv.velexauto.velex.HelperClasses;

/**
 * Created by Dinjvald on 07/12/15.
 */

public class AgreementRequestBody {

    long agreementId;
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
    double price;
    double valueAddedTax;
    int paymentTerm;
    String driver;
    String plateNr;
    String invoiceSendDate;
    String notes;

    public long getAgreementId() {
        return agreementId;
    }

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

    public double getPrice() {
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

}
