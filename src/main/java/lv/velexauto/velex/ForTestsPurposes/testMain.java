package lv.velexauto.velex.ForTestsPurposes;

import lv.velexauto.velex.HelperClasses.DateAssistant;

import javax.sound.midi.Soundbank;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Dinjvald on 20/10/15.
 */
public class testMain {

    public static void main(String[] args) throws ParseException {

        DateAssistant dateAssistant = new DateAssistant();
        Date date = dateAssistant.stringToDate("01.01.1971");
        long milli = date.getTime();
        System.out.println(milli);

        Date date1 = new Date(milli);
        System.out.println(date1);

    }
}
