package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DatePicker {

    private static final Logger logger = LoggerFactory.getLogger(DatePicker.class.getName());
    private static Locale locale = new Locale("ru");

    public String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd MMM yyyy", locale);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        return dateFormat.format(date);
    }

    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd MMM yyyy", locale);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        String strDate = dateFormat.format(new Date());
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(strDate));
        } catch (ParseException e) {
            logger.error(e.toString());
        }
        calendar.add(Calendar.DATE, 1);  // number of days to add
        strDate = dateFormat.format(calendar.getTime());  // dt is now the new date
        String stringDate = strDate.replaceAll("\\.","");
        logger.info(stringDate);
        return stringDate;
    }

}
