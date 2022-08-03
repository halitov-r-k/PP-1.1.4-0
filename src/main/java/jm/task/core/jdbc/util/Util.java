package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соединения с БД
    public static Connection getConnection() {
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            inputStream = Files.newInputStream(Paths.get("database.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            System.out.println("Driver OK");
        } catch (Exception e) {
            System.out.println("Driver No");
        }

        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Util: Close connection");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
