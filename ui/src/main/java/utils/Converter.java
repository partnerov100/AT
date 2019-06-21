package utils;

import org.apache.commons.lang3.StringUtils;

public class Converter {
    private Converter(){
    }

    /**
     * Конвертируем пробелы в неразрывные пробелы
     */
    public static String spaceToNbsp(String text){
        return text.replace(" ", " ");
    }

    /**
     * Конвертируем неразрывные пробелы(alt+255) в пробелы
     */
    public static String nbspToSpace(String text){
        return text.replace(" ", " ");
    }

    public static String hidePenny(String price) {
        String pennies = StringUtils.substringBetween(price, ",", "₽");
        return price.replace("," + pennies, " ");
    }

}
