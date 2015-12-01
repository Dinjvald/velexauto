package lv.velexauto.velex.ForTestsPurposes;

import com.google.gson.Gson;
import lv.velexauto.velex.HelperClasses.DateAssistant;
import lv.velexauto.velex.domain.Agreement;

import java.text.ParseException;

/**
 * Created by Dinjvald on 03/11/15.
 */
public class GSONTest {

    public static void main(String[] args) throws ParseException {

        DateAssistant dateAssistant = new DateAssistant();

        Agreement agreement = new Agreement.AgreementBuilder()
                .agreementNr("xxx111")
                .invoiceNr("1015/50")
                .clientName("Kreiss")
                .loadingDate(dateAssistant.stringToDate("20.10.2015"))
                .loadingAddress("Maskavas 250")
                .unloadingDate(dateAssistant.stringToDate("25.10.2015"))
                .unloadingAddress("Kurzemes 102")
                .price(100.15)
                .valueAddedTax(100.23)
                .paymentTerm(30)
                .invoiceSendDate(dateAssistant.stringToDate("30.10.2015"))
                .estimatedDateOfPayment(dateAssistant.stringToDate("30.11.2015"))
                .onBehalfOf("Cmr 001")
                .fileLinkAgreement("http//www/velexauto.lv")
                .fileLinkInvoice("http://www.kreiss.lv")
                .notes("no notes")
                .build();

        Gson gson = new Gson();

        String json = gson.toJson(agreement);

        System.out.println(json);

        Agreement agreementFromJson = gson.fromJson(json, Agreement.class);

        System.out.println(agreementFromJson.getClientName());
    }

}
