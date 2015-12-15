package lv.velexauto.velex.domain;

/**
 * Created by Dinjvald on 23/03/15.
 */

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "agreement")
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "agreement_id", columnDefinition = "int(11)")
    private long agreementId;

    @Transient
    private long employeeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Transient
    private long company_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "agreement_nr", columnDefinition = "char(200)")
    private String agreementNr;

    @Column(name = "invoice_nr", columnDefinition = "char(200)")
    private String invoiceNr;

    @Column(name = "client_name", columnDefinition = "char(200)")
    private String clientName;

    @Column(name = "loading_date", columnDefinition = "date")
    @Temporal(TemporalType.DATE)
    private java.util.Date loadingDate;

    @Column(name = "loading_address", columnDefinition = "char(200)")
    private String loadingAddress;

    @Column(name = "unloading_date", columnDefinition = "date")
    @Temporal(TemporalType.DATE)
    private java.util.Date unloadingDate;

    @Column(name = "unloading_address", columnDefinition = "char(200)")
    private String unloadingAddress;

    @Column(name = "driver", columnDefinition = "char(200)")
    private String driver;

    @Column(name = "plate_nr", columnDefinition = "char(200)")
    private String plateNr;

    @Column(name = "price", columnDefinition = "float(11,2)")
    private double price = -1.00;

    @Column(name = "value_added_tax", columnDefinition = "float(11,2)")
    private double valueAddedTax;

    @Column(name = "payment_term", columnDefinition = "int(11)")
    private int paymentTerm;

    @Column(name = "invoice_send_date", columnDefinition = "date")
    @Temporal(TemporalType.DATE)
    private java.util.Date invoiceSendDate;

    @Column(name = "estimated_date_of_payment", columnDefinition = "date")
    @Temporal(TemporalType.DATE)
    private java.util.Date estimatedDateOfPayment;

    @Column(name = "on_behalf_of", columnDefinition = "char(200)")
    private String onBehalfOf;

    @Column(name = "file_link_agreement", columnDefinition = "text")
    private String fileLinkAgreement;

    @Column(name = "file_link_invoice", columnDefinition = "text")
    private String fileLinkInvoice;

    @Column(name = "notes", columnDefinition = "text")
    private String notes;

    @Column(name = "is_paid", columnDefinition = "char(1)")
    @Type(type = "org.hibernate.type.TrueFalseType")
    private boolean isPaid;

    public Agreement() {

    }

    private Agreement(AgreementBuilder ab) {
        this.employee = ab.employeeBuilder;
        this.company = ab.companyBuilder;
        this.agreementNr = ab.agreementNrBuilder;
        this.invoiceNr = ab.invoiceNrBuilder;
        this.clientName = ab.clientNameBuilder;
        this.loadingDate = ab.loadingDateBuilder;
        this.loadingAddress = ab.loadingAddressBuilder;
        this.unloadingDate = ab.unloadingDateBuilder;
        this.unloadingAddress = ab.unloadingAddressBuilder;
        this.driver = ab.driverBuilder;
        this.plateNr = ab.plateNrBuilder;
        this.price = ab.priceBuilder;
        this.valueAddedTax = ab.valueAddedTaxBuilder;
        this.paymentTerm = ab.paymentTermBuilder;
        this.invoiceSendDate = ab.invoiceSendDateBuilder;
        this.estimatedDateOfPayment = ab.estimatedDateOfPaymentBuilder;
        this.onBehalfOf = ab.onBehalfOfBuilder;
        this.fileLinkAgreement = ab.fileLinkAgreementBuilder;
        this.fileLinkInvoice = ab.fileLinkInvoiceBuilder;
        this.notes = ab.notesBuilder;
        this.isPaid = ab.isPaidBuilder;
    }

    public static class AgreementBuilder {
        private Employee employeeBuilder;
        private Company companyBuilder;
        private String agreementNrBuilder;
        private String invoiceNrBuilder;
        private String clientNameBuilder;
        private java.util.Date loadingDateBuilder;
        private String loadingAddressBuilder;
        private java.util.Date unloadingDateBuilder;
        private String unloadingAddressBuilder;
        private String driverBuilder;
        private String plateNrBuilder;
        private double priceBuilder;
        private double valueAddedTaxBuilder;
        private int paymentTermBuilder;
        private java.util.Date invoiceSendDateBuilder;
        private java.util.Date estimatedDateOfPaymentBuilder;
        private String onBehalfOfBuilder;
        private String fileLinkAgreementBuilder;
        private String fileLinkInvoiceBuilder;
        private String notesBuilder;
        private boolean isPaidBuilder;

        public AgreementBuilder employee(Employee newEmployee) {
            this.employeeBuilder = newEmployee;
            return this;
        }

        public AgreementBuilder company(Company newCompany) {
            this.companyBuilder = newCompany;
            return this;
        }

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

        public AgreementBuilder loadingDate(java.util.Date newLoadingDate) {
            this.loadingDateBuilder = newLoadingDate;
            return this;
        }

        public AgreementBuilder loadingAddress(String newLoadingAddress) {
            this.loadingAddressBuilder = newLoadingAddress;
            return this;
        }

        public AgreementBuilder unloadingDate(java.util.Date newUnloadingDate) {
            this.unloadingDateBuilder = newUnloadingDate;
            return this;
        }

        public AgreementBuilder unloadingAddress(String newUnloadingAddress) {
            this.unloadingAddressBuilder = newUnloadingAddress;
            return this;
        }

        public AgreementBuilder driver(String newDriver) {
            this.driverBuilder = newDriver;
            return this;
        }

        public AgreementBuilder plateNr(String newPlateNr) {
            this.plateNrBuilder = newPlateNr;
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

        public AgreementBuilder paymentTerm(int newPaymentTerm) {
            this.paymentTermBuilder = newPaymentTerm;
            return this;
        }

        public AgreementBuilder invoiceSendDate(java.util.Date newInvoiceSendDate) {
            this.invoiceSendDateBuilder = newInvoiceSendDate;
            return this;
        }

        public AgreementBuilder estimatedDateOfPayment(java.util.Date newEstimatedDateOfPayment) {
            this.estimatedDateOfPaymentBuilder = newEstimatedDateOfPayment;
            return this;
        }

        public AgreementBuilder onBehalfOf(String newOnBehalfOf) {
            this.onBehalfOfBuilder = newOnBehalfOf;
            return this;
        }

        public AgreementBuilder fileLinkAgreement(String newFileLinkAgreement) {
            this.fileLinkAgreementBuilder = newFileLinkAgreement;
            return this;
        }

        public AgreementBuilder fileLinkInvoice(String newFileLinkInvoice) {
            this.fileLinkInvoiceBuilder = newFileLinkInvoice;
            return this;
        }

        public AgreementBuilder notes(String newNotes) {
            this.notesBuilder = newNotes;
            return this;
        }

        public AgreementBuilder isPaid(boolean newIsPaid) {
            this.isPaidBuilder = newIsPaid;
            return this;
        }

        public Agreement build() {
            return new Agreement(this);
        }
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public java.util.Date getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(java.util.Date loadingDate) {
        this.loadingDate = loadingDate;
    }

    public String getLoadingAddress() {
        return loadingAddress;
    }

    public void setLoadingAddress(String loadingAddress) {
        this.loadingAddress = loadingAddress;
    }

    public java.util.Date getUnloadingDate() {
        return unloadingDate;
    }

    public void setUnloadingDate(java.util.Date unloadingDate) {
        this.unloadingDate = unloadingDate;
    }

    public String getUnloadingAddress() {
        return unloadingAddress;
    }

    public void setUnloadingAddress(String unloadingAddress) {
        this.unloadingAddress = unloadingAddress;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getPlateNr() {
        return plateNr;
    }

    public void setPlateNr(String plateNr) {
        this.plateNr = plateNr;
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

    public int getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(int paymentTerm) {
        this.paymentTerm = paymentTerm;
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

    public java.util.Date getInvoiceSendDate() {
        return invoiceSendDate;
    }

    public void setInvoiceSendDate(java.util.Date invoiceSendDate) {
        this.invoiceSendDate = invoiceSendDate;
    }

    public java.util.Date getEstimatedDateOfPayment() {
        return estimatedDateOfPayment;
    }

    public void setEstimatedDateOfPayment(java.util.Date estimatedDateOfPayment) {
        this.estimatedDateOfPayment = estimatedDateOfPayment;
    }

    public String getOnBehalfOf() {
        return onBehalfOf;
    }

    public void setOnBehalfOf(String onBehalfOf) {
        this.onBehalfOf = onBehalfOf;
    }

    public String getFileLinkAgreement() {
        return fileLinkAgreement;
    }

    public void setFileLinkAgreement(String fileLinkAgreement) {
        this.fileLinkAgreement = fileLinkAgreement;
    }

    public String getFileLinkInvoice() {
        return fileLinkInvoice;
    }

    public void setFileLinkInvoice(String fileLinkInvoice) {
        this.fileLinkInvoice = fileLinkInvoice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
}
