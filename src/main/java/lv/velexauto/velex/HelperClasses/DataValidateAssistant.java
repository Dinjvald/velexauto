package lv.velexauto.velex.HelperClasses;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dinjvald on 11/12/15.
 */

@Component("DataValidateAssistant")
public class DataValidateAssistant {

    public static final String DEFAULT_DATE = "01.01.1971";
    public static final String DEFAULT_TEXT = "empty";
    public static final int DEFAULT_INT = -1;
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String CANT_DELETE = "can't delete";

    @Autowired
    @Qualifier("DateAssistant")
    private DateAssistant dateAssistant;

    public HashMap getDefaultValuesMap() {
        return new HashMap(){{
            put("defDate", DEFAULT_DATE);
            put("defInt", DEFAULT_INT);
            put("defText", DEFAULT_TEXT);
        }};
    }

    public String alertSuccess(String type) {
        List<String> response = new ArrayList<>();
        response.add(SUCCESS);
        response.add(type);
        Gson gson = new Gson();
        return gson.toJson(response);
    }

    public String alertError(String type) {
        List<String> response = new ArrayList<>();
        response.add(ERROR);
        response.add(type);
        Gson gson = new Gson();
        return gson.toJson(response);
    }

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

    /*
    * This method doesn't check the validity of agreementID
    */
    private boolean isAgreementNumbersValid (AgreementRequestBody agreementRB) {
        int paymentTerm = agreementRB.getPaymentTerm();
        double number = agreementRB.getPrice();

        if (paymentTerm > 3000 || paymentTerm < DEFAULT_INT) return false;
        if (number < DEFAULT_INT) return false;
        number = agreementRB.getValueAddedTax();
        if (number < DEFAULT_INT) return false;
        return true;
    }
}
