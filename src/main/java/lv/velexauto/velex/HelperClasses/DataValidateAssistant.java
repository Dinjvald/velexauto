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

    @Autowired
    @Qualifier("DateAssistant")
    private DateAssistant dateAssistant;

    public boolean isAgreementRequestBodyValid(AgreementRequestBody agreementRB) {

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
        text.clear();
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



        int paymentTerm = agreementRB.getPaymentTerm();
        if (paymentTerm > 3000 || paymentTerm < -1) return false;

        return true;
    }
}
