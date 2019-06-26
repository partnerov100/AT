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

    /**
     * Берется текущая дата, добавляется кол-во дней и возвращается в необходимом для поиска формате
     */
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
        calendar.add(Calendar.DATE, 1);  //дней добавлено к текущей дате
        strDate = dateFormat.format(calendar.getTime());
        String stringDate = strDate.replaceAll("\\.","").toLowerCase(); //приводим к нужному формату
        logger.info(stringDate);
        return stringDate;
    }

}
