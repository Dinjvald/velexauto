package lv.velexauto.velex.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Dinjvald on 23/11/15.
 */
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id", columnDefinition = "int(11)")
    private long employeeId;

    @Transient
    private long company_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "name", columnDefinition = "char(50)", nullable = false)
    private String name;

    @Column(name = "surname", columnDefinition = "char(50)", nullable = false)
    private String surname;

    @Column(name = "passport_nr", columnDefinition = "char(100)", nullable = false)
    private String passportNr;

    @Column(name = "personal_code", columnDefinition = "char(50)")
    private String personalCode;

    @Column(name = "e_mail", columnDefinition = "char(100)")
    private String eMail;

    @Column(name = "telephone_nr", columnDefinition = "char(50)")
    private String telephoneNr;

    @Column(name = "is_active", columnDefinition = "char(1)", nullable = false)
    @Type(type = "org.hibernate.type.TrueFalseType")
    private boolean isActive;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Agreement> agreements;

    @OneToOne(mappedBy = "employee", fetch = FetchType.EAGER)
    private User user;

    public Employee() {

    }

    private Employee(EmployeeBuilder eb) {
        this.company = eb.companyBuilder;
        this.name = eb.nameBuilder;
        this.surname = eb.surnameBuilder;
        this.passportNr = eb.passportNrBuilder;
        this.personalCode = eb.personalCodeBuilder;
        this.eMail = eb.eMailBuilder;
        this.telephoneNr = eb.telephoneNrBuilder;
        this.isActive = eb.isActiveBuilder;
    }

    public static class EmployeeBuilder {
        private Company companyBuilder;
        private String nameBuilder;
        private String surnameBuilder;
        private String passportNrBuilder;
        private String personalCodeBuilder;
        private String eMailBuilder;
        private String telephoneNrBuilder;
        private boolean isActiveBuilder;

        public EmployeeBuilder company(Company newCompany) {
            this.companyBuilder = newCompany;
            return this;
        }

        public EmployeeBuilder name(String newName) {
            this.nameBuilder = newName;
            return this;
        }

        public EmployeeBuilder surname(String newSurname) {
            this.surnameBuilder = newSurname;
            return this;
        }

        public EmployeeBuilder passportNr(String newPassportNr) {
            this.passportNrBuilder = newPassportNr;
            return this;
        }

        public EmployeeBuilder personalCode(String newPersonalCode) {
            this.personalCodeBuilder = newPersonalCode;
            return this;
        }

        public EmployeeBuilder eMail(String newEMail) {
            this.eMailBuilder = newEMail;
            return this;
        }

        public EmployeeBuilder telephoneNr(String newTelephoneNr) {
            this.telephoneNrBuilder = newTelephoneNr;
            return this;
        }

        public EmployeeBuilder isActive(boolean newIsActive) {
            this.isActiveBuilder = newIsActive;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassportNr() {
        return passportNr;
    }

    public void setPassportNr(String passportNr) {
        this.passportNr = passportNr;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelephoneNr() {
        return telephoneNr;
    }

    public void setTelephoneNr(String telephoneNr) {
        this.telephoneNr = telephoneNr;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<Agreement> getAgreements() {
        return agreements;
    }

    public User getUser() {
        return user;
    }
}
