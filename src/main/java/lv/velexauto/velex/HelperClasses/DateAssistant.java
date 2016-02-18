package lv.velexauto.velex.HelperClasses;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
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

    public java.util.Date getCurrentSystemDateWithoutTimestamp() {
        LocalDate localDate = LocalDate.now();
        return fromLocalDate(localDate);
    }

    public java.util.Date fromLocalDate(LocalDate date) {
        Instant instant = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public java.util.Date addDaysToDate(java.util.Date date, int days) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public java.util.Date subtractDaysFromDate(java.util.Date date, int days) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public java.util.Date getFirstDateOfCurrentMonth(java.util.Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        dayOfMonth -= 1;
        dayOfMonth *= -1;
        return subtractDaysFromDate(date, dayOfMonth);
    }

    public java.util.Date getFirstDateOfCurrentQuarter(java.util.Date date) throws ParseException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);
        int calendarYearQuarter = ((currentMonth - 1) / 3) + 1;
        switch (calendarYearQuarter) {
            case 1: default:
                return stringToDate("01.01." + currentYear);
            case 2:
                return stringToDate("01.04." + currentYear);
            case 3:
                return stringToDate("01.07." + currentYear);
            case 4:
                return stringToDate("01.10." + currentYear);
        }
    }

    public java.util.Date getFirstDateOfCurrentHalfYear(java.util.Date date) throws ParseException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentYear = calendar.get(Calendar.YEAR);
        if (currentMonth < 7) {
            return stringToDate("01.01." + currentYear);
        } else {
            return stringToDate("01.07." + currentYear);
        }
    }

    public java.util.Date getFirstDateOfCurrentYear(java.util.Date date) throws ParseException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int currentYear = calendar.get(Calendar.YEAR);
        return stringToDate("01.01." + currentYear);
    }

    public java.util.Date getFirstDateOfLastYear(java.util.Date date) throws ParseException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int lastYear = calendar.get(Calendar.YEAR) - 1;
        return stringToDate("01.01." + lastYear);
    }

}
