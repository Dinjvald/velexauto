package lv.velexauto.velex.HelperClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        String[] parts = date.split("\\.");
        if (parts.length != 3) return false;

        int day = Integer.parseInt(parts[0]);
        if (day < 1 || day > 31) return false;

        int month = Integer.parseInt(parts[1]);
        if (month < 1 || month > 12) return false;

        int year = Integer.parseInt(parts[2]);
        if (year < 1900 || year > 2100) return false;

        try {
            stringToDate(date);
        } catch (ParseException pr) {
            pr.printStackTrace();
            return false;
        }
        return true;
    }

}
