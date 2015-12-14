package lv.velexauto.velex.ForTestsPurposes;

import lv.velexauto.velex.HelperClasses.DateAssistant;

import java.text.ParseException;

/**
 * Created by Dinjvald on 20/10/15.
 */
public class testMain {

    public static void main(String[] args) {

        DateAssistant dateAssistant = new DateAssistant();
        try {
            java.util.Date date = dateAssistant.stringToDate("01.01.1971");
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
