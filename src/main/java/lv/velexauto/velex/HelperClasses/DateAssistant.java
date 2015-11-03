package lv.velexauto.velex.HelperClasses;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        return sdf.parse(date);
    }

    public String checkStringDateFormat(String date) {

        String[] parts = date.split("\\.");
        if (parts.length != 3) return "The date format is incorrect";

        int day = Integer.parseInt(parts[0]);
        if (day < 1 || day > 31) return "The day value is incorrect";

        int month = Integer.parseInt(parts[1]);
        if (month < 1 || month > 12) return "The month value is incorrect";

        int year = Integer.parseInt(parts[2]);
        if (year < 1000 || year > 9999) return "The year value is incorrect";

        return null;
    }

}
