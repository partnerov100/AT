package utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.invoke.MethodHandles;
import java.sql.*;
import java.util.Objects;

public final class PostgreSQL {
    private PostgreSQL(){
    }

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static String jdbc = Props.getEnvData("db.jdbc");
    private static String dbUrl =  Props.getEnvData("db.url");
    private static String dbPort = Props.getEnvData("db.port");
    private static String dbName = Props.getEnvData("db.name");
    private static String host = jdbc + "://" + dbUrl + ":" + dbPort + "/" +dbName;

    private static String username = Props.getEnvData("db.user");
    private static String password = Props.getEnvData("db.password");

    private static Connection connection;

    private static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(host, username, password);
                logger.info("Create DB connect");
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }
        return connection;
    }

    public static String getCode() {
        String sqlRequest = "SELECT \"Text\" FROM NOTIFY.\"Message\" WHERE \"MessageType\" IN (E'2') ORDER BY \"Id\" DESC LIMIT 1;";
        ResultSet resultSet;
        String text = null;
        try {
            Statement statement = getInstance().createStatement();
            resultSet = statement.executeQuery(sqlRequest);
            resultSet.next();
            text = Objects.requireNonNull(resultSet).getString("Text");
        } catch (NullPointerException|SQLException e) {
            logger.error(e.toString());
        }
        return StringUtils.substringAfter(text, ": ");
    }

    public static String waitNewCode(String oldCode){
        String newCode = oldCode;
        while(oldCode.equals(newCode)){
            try {
                Thread.sleep(700);
            } catch (Exception e) {
                logger.error(e.toString());
            }
            newCode = getCode();
        }
        return newCode;


    }
}
