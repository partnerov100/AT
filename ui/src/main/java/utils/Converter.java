package utils;

public class Converter {

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
}
