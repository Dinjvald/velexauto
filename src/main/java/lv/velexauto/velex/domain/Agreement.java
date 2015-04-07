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

    public Agreement() {

    }

    public Agreement(String agreementNr, String loadingDate, String loadingAddress, String unloadingDate,
                     String unloadingAddress, double price, double valueAddedTax) {
        this.agreementNr = agreementNr;
        this.loadingDate = loadingDate;
        this.loadingAddress = loadingAddress;
        this.unloadingDate = unloadingDate;
        this.unloadingAddress = unloadingAddress;
        this.price = price;
        this.valueAddedTax = valueAddedTax;
    }

    public long getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(long agreementId) {
        this.agreementId = agreementId;
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
}
