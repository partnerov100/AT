package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.util.Properties;

public class Props {

    private Props(){
    }

    private static String server = "dev";

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static String getFileProperties(String name, String key) {
        Properties property = new Properties();
        URL props = ClassLoader.getSystemResource("properties/" + name + ".properties");
        String text = null;
        try {
            property.load(new InputStreamReader(props.openStream(), StandardCharsets.UTF_8));
            text = property.getProperty(key);
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return text;
    }

    public static String getEnvData(String key) {
        String serverProp = System.getProperty("server");
        if(serverProp==null) {
            serverProp = server;
            System.setProperty("server", serverProp);
        }
        return getFileProperties(serverProp, key);
    }

    public static String getData(String key) {
        return getFileProperties("data", key);
    }

    public static String getLocaleProp(String locale, String key) {
        return getFileProperties(locale, key);
    }

}
