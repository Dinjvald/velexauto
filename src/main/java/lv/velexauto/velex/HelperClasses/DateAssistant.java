package lv.velexauto.velex.HelperClasses;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Dinjvald on 02/11/15.
 */

@Component("DateAssistant")
public class DateAssistant {

    public String dateToString(java.util.Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }

    public java.util.Date stringToDate(String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);
        return sdf.parse(date);
    }

    public boolean isDateValid(String date) {

        try {
            stringToDate(date);
        } catch (ParseException pr) {
            pr.printStackTrace();
            return false;
        }
        return true;
    }

    public java.util.Date addDaysToDate(java.util.Date date, int days) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

}
