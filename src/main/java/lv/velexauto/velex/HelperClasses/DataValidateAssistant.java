package lv.velexauto.velex.HelperClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinjvald on 11/12/15.
 */

@Component("DataValidateAssistant")
public class DataValidateAssistant {

    private final int INT_DEFAULT = -1;

    @Autowired
    @Qualifier("DateAssistant")
    private DateAssistant dateAssistant;

    public boolean isAgreementRequestBodyValid(AgreementRequestBody agreementRB) {
        if (!isAgreementTextValid(agreementRB)) return false;
        if (!isAgreementDatesValid(agreementRB)) return false;
        if (!isAgreementNumbersValid(agreementRB)) return false;
        return true;
    }

    private boolean isAgreementTextValid (AgreementRequestBody agreementRB) {
        List<String> text = new ArrayList<String>();

        text.add(agreementRB.getLoadingAddress());
        text.add(agreementRB.getUnloadingAddress());
        text.add(agreementRB.getClientName());
        text.add(agreementRB.getAgreementNr());
        text.add(agreementRB.getInvoiceNr());
        text.add(agreementRB.getOnBehalfOf());
        text.add(agreementRB.getDriver());
        text.add(agreementRB.getPlateNr());

        for (String line : text) {
            if (line.length() > 200) return false;
        }
        return true;
    }

    private boolean isAgreementDatesValid (AgreementRequestBody agreementRB) {
        List<String> text = new ArrayList<String>();

        text.add(agreementRB.getLoadingDate());
        text.add(agreementRB.getUnloadingDate());
        text.add(agreementRB.getInvoiceSendDate());

        for (String line : text) {
            if (line != null) {
                if (!dateAssistant.isDateValid(line)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isAgreementNumbersValid (AgreementRequestBody agreementRB) {
        int paymentTerm = agreementRB.getPaymentTerm();
        double number = agreementRB.getPrice();

        if (paymentTerm > 3000 || paymentTerm < INT_DEFAULT) return false;
        if (number < INT_DEFAULT) return false;
        number = agreementRB.getValueAddedTax();
        if (number < INT_DEFAULT) return false;
        return true;
    }
}
