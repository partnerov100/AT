package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.sql.*;

public class PostgreSQL {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static String jdbc = Props.getEnvData("db.jdbc");
    private static String dbUrl =  Props.getEnvData("db.url");
    private static String dbPort = Props.getEnvData("db.port");
    private static String dbName = Props.getEnvData("db.name");
    private static String host = jdbc + "://" + dbUrl + ":" + dbPort + "/" +dbName;

    private static String username = Props.getEnvData("db.user");
    private static String password = Props.getEnvData("db.password");

    private static Connection connection;

    public synchronized static Connection getInstance() {
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

    public static void findCode(String num) throws SQLException {

        try {
            Statement statement = getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from notify.message where lastid = '" + num + "'");
            resultSet.next();

        } catch (NullPointerException e) {
            logger.error(e.toString());
        }

    }
}
