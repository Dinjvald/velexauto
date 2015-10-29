package lv.velexauto.velex.domain;

/**
 * Created by Dinjvald on 23/03/15.
 */

import javax.persistence.*;

@Entity
@Table(name = "agreement")
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "agreement_id", columnDefinition = "int(11)")
    private long agreementId;

    @Column(name = "agreement_nr", columnDefinition = "char(15)")
    private String agreementNr;

    @Column(name = "invoice_nr", columnDefinition = "char(15)")
    private String invoiceNr;

    @Column(name = "client_name", columnDefinition = "char(50)")
    private String clientName;

    @Column(name = "loading_date", columnDefinition = "char(10)")
    private String loadingDate;

    @Column(name = "loading_address", columnDefinition = "char(100)")
    private String loadingAddress;

    @Column(name = "unloading_date", columnDefinition = "char(10)")
    private String unloadingDate;

    @Column(name = "unloading_address", columnDefinition = "char(100)")
    private String unloadingAddress;

    @Column(name = "price", columnDefinition = "float(6,2)")
    private double price;

    @Column(name = "value_added_tax", columnDefinition = "float(6,2)")
    private double valueAddedTax;

    @Column(name = "invoice_send_date", columnDefinition = "char(10)")
    private String invoiceSendDate;

    @Column(name = "estimated_date_of_payment", columnDefinition = "char(10)")
    private String estimatedDateOfPayment;

    @Column(name = "on_behalf_of", columnDefinition = "char(100)")
    private String onBehalfOf;

    public Agreement() {

    }

    private Agreement(AgreementBuilder ab) {
        this.agreementNr = ab.agreementNrBuilder;
        this.invoiceNr = ab.invoiceNrBuilder;
        this.clientName = ab.clientNameBuilder;
        this.loadingDate = ab.loadingDateBuilder;
        this.loadingAddress = ab.loadingAddressBuilder;
        this.unloadingDate = ab.unloadingDateBuilder;
        this.unloadingAddress = ab.unloadingAddressBuilder;
        this.price = ab.priceBuilder;
        this.valueAddedTax = ab.valueAddedTaxBuilder;
        this.invoiceSendDate = ab.invoiceSendDateBuilder;
        this.estimatedDateOfPayment = ab.estimatedDateOfPaymentBuilder;
        this.onBehalfOf = ab.onBehalfOfBuilder;
    }

    public static class AgreementBuilder {
        private String agreementNrBuilder;
        private String invoiceNrBuilder;
        private String clientNameBuilder;
        private String loadingDateBuilder;
        private String loadingAddressBuilder;
        private String unloadingDateBuilder;
        private String unloadingAddressBuilder;
        private double priceBuilder;
        private double valueAddedTaxBuilder;
        private String invoiceSendDateBuilder;
        private String estimatedDateOfPaymentBuilder;
        private String onBehalfOfBuilder;

        public AgreementBuilder agreementNr(String newAgreementNr) {
            this.agreementNrBuilder = newAgreementNr;
            return this;
        }

        public AgreementBuilder invoiceNr(String newInvoiceNr) {
            this.invoiceNrBuilder = newInvoiceNr;
            return this;
        }

        public AgreementBuilder clientName(String newClientName) {
            this.clientNameBuilder = newClientName;
            return this;
        }

        public AgreementBuilder loadingDate(String newLoadingDate) {
            this.loadingDateBuilder = newLoadingDate;
            return this;
        }

        public AgreementBuilder loadingAddress(String newLoadingAddress) {
            this.loadingAddressBuilder = newLoadingAddress;
            return this;
        }

        public AgreementBuilder unloadingDate(String newUnloadingDate) {
            this.unloadingDateBuilder = newUnloadingDate;
            return this;
        }

        public AgreementBuilder unloadingAddress(String newUnloadingAddress) {
            this.unloadingAddressBuilder = newUnloadingAddress;
            return this;
        }

        public AgreementBuilder price(double newPrice) {
            this.priceBuilder = newPrice;
            return this;
        }

        public AgreementBuilder valueAddedTax(double newValueAddedTax) {
            this.valueAddedTaxBuilder = newValueAddedTax;
            return this;
        }

        public AgreementBuilder invoiceSendDate(String newInvoiceSendDate) {
            this.invoiceSendDateBuilder = newInvoiceSendDate;
            return this;
        }

        public AgreementBuilder estimatedDateOfPayment(String newEstimatedDateOfPayment) {
            this.estimatedDateOfPaymentBuilder = newEstimatedDateOfPayment;
            return this;
        }

        public AgreementBuilder onBehalfOf(String newOnBehalfOf) {
            this.onBehalfOfBuilder = newOnBehalfOf;
            return this;
        }

        public Agreement build() {
            return new Agreement(this);
        }
    }

    public Agreement(String agreementNr, String invoiceNr, String clientName, String loadingDate, String loadingAddress,
                     String unloadingDate, String unloadingAddress, double price, double valueAddedTax,
                     String invoiceSendDate, String estimatedDateOfPayment, String onBehalfOf) {
        this.agreementNr = agreementNr;
        this.invoiceNr = invoiceNr;
        this.clientName = clientName;
        this.loadingDate = loadingDate;
        this.loadingAddress = loadingAddress;
        this.unloadingDate = unloadingDate;
        this.unloadingAddress = unloadingAddress;
        this.price = price;
        this.valueAddedTax = valueAddedTax;
        this.invoiceSendDate = invoiceSendDate;
        this.estimatedDateOfPayment = estimatedDateOfPayment;
        this.onBehalfOf = onBehalfOf;
    }

    public long getAgreementId() {
        return agreementId;
    }

    public String getAgreementNr() {
        return agreementNr;
    }

    public void setAgreementNr(String agreementNr) {
        this.agreementNr = agreementNr;
    }

    public String getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(String loadingDate) {
        this.loadingDate = loadingDate;
    }

    public String getLoadingAddress() {
        return loadingAddress;
    }

    public void setLoadingAddress(String loadingAddress) {
        this.loadingAddress = loadingAddress;
    }

    public String getUnloadingDate() {
        return unloadingDate;
    }

    public void setUnloadingDate(String unloadingDate) {
        this.unloadingDate = unloadingDate;
    }

    public String getUnloadingAddress() {
        return unloadingAddress;
    }

    public void setUnloadingAddress(String unloadingAddress) {
        this.unloadingAddress = unloadingAddress;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getValueAddedTax() {
        return valueAddedTax;
    }

    public void setValueAddedTax(double valueAddedTax) {
        this.valueAddedTax = valueAddedTax;
    }

    public String getInvoiceNr() {
        return invoiceNr;
    }

    public void setInvoiceNr(String invoiceNr) {
        this.invoiceNr = invoiceNr;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getInvoiceSendDate() {
        return invoiceSendDate;
    }

    public void setInvoiceSendDate(String invoiceSendDate) {
        this.invoiceSendDate = invoiceSendDate;
    }

    public String getEstimatedDateOfPayment() {
        return estimatedDateOfPayment;
    }

    public void setEstimatedDateOfPayment(String estimatedDateOfPayment) {
        this.estimatedDateOfPayment = estimatedDateOfPayment;
    }

    public String getOnBehalfOf() {
        return onBehalfOf;
    }

    public void setOnBehalfOf(String onBehalfOf) {
        this.onBehalfOf = onBehalfOf;
    }
}
