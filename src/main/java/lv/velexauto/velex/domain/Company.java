package lv.velexauto.velex.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Dinjvald on 23/11/15.
 */

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id", columnDefinition = "int(11)", nullable = false)
    private long companyId;

    @Column(name = "name", columnDefinition = "char(255)", nullable = false)
    private String name;

    @Column(name = "registration_nr", columnDefinition = "char(50)", nullable = false)
    private String registrationNr;

    @Column(name = "vat_nr", columnDefinition = "char(50)", nullable = false)
    private String vatNr;

    @Column(name = "establishment_date", columnDefinition = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private java.util.Date establishmentDate;

    @Column(name = "registration_date", columnDefinition = "date")
    @Temporal(TemporalType.DATE)
    private java.util.Date registrationDate;

    @Column(name = "address", columnDefinition = "char(255)", nullable = false)
    private String address;

    @Column(name = "legal_address", columnDefinition = "char(255)", nullable = false)
    private String legalAddress;

    @Column(name = "telephone_nr", columnDefinition = "char(30)")
    private String telephoneNr;

    @Column(name = "e_mail", columnDefinition = "char(30)")
    private String eMail;

    @Column(name = "web", columnDefinition = "char(200)")
    private String web;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Employee> employees;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Agreement> agreements;

    public Company() {

    }

    private Company(CompanyBuilder cb) {
        this.name = cb.nameBuilder;
        this.registrationNr = cb.registrationNrBuilder;
        this.vatNr = cb.vatNrBuilder;
        this.establishmentDate = cb.establishmentDateBuilder;
        this.registrationDate = cb.registrationDateBuilder;
        this.address = cb.addressBuilder;
        this.legalAddress = cb.legalAddressBuilder;
        this.telephoneNr = cb.telephoneNrBuilder;
        this.eMail = cb.eMailBuilder;
        this.web = cb.webBuilder;
    }

    public static class CompanyBuilder {
        private String nameBuilder;
        private String registrationNrBuilder;
        private String vatNrBuilder;
        private java.util.Date establishmentDateBuilder;
        private java.util.Date registrationDateBuilder;
        private String addressBuilder;
        private String legalAddressBuilder;
        private String telephoneNrBuilder;
        private String eMailBuilder;
        private String webBuilder;

        public CompanyBuilder name(String newName) {
            this.nameBuilder = newName;
            return this;
        }

        public CompanyBuilder registrationNr(String newRegistrationNr) {
            this.registrationNrBuilder = newRegistrationNr;
            return this;
        }

        public CompanyBuilder vatNr(String newVatNr) {
            this.vatNrBuilder = newVatNr;
            return this;
        }

        public CompanyBuilder establishmentDate(java.util.Date newEstablishmentDate) {
            this.establishmentDateBuilder = newEstablishmentDate;
            return this;
        }

        public CompanyBuilder registrationDate(java.util.Date newRegistrationDate) {
            this.registrationDateBuilder = newRegistrationDate;
            return this;
        }

        public CompanyBuilder address(String newAddress) {
            this.addressBuilder = newAddress;
            return this;
        }

        public CompanyBuilder legalAddress(String newLegalAddress) {
            this.legalAddressBuilder = newLegalAddress;
            return this;
        }

        public CompanyBuilder telephoneNr(String newTelephoneNr) {
            this.telephoneNrBuilder = newTelephoneNr;
            return this;
        }

        public CompanyBuilder eMail(String newEMail) {
            this.eMailBuilder = newEMail;
            return this;
        }

        public CompanyBuilder web(String newWeb) {
            this.webBuilder = newWeb;
            return this;
        }

        public Company build() {
            return new Company(this);
        }
    }

    public long getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNr() {
        return registrationNr;
    }

    public void setRegistrationNr(String registrationNr) {
        this.registrationNr = registrationNr;
    }

    public String getVatNr() {
        return vatNr;
    }

    public void setVatNr(String vatNr) {
        this.vatNr = vatNr;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getTelephoneNr() {
        return telephoneNr;
    }

    public void setTelephoneNr(String telephoneNr) {
        this.telephoneNr = telephoneNr;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Agreement> getAgreements() {
        return agreements;
    }
}
