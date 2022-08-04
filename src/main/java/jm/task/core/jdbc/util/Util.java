package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    static Logger LOGGER = Logger.getLogger(Util.class.getName());
    // реализуйте настройку соединения с БД
    public static Connection getConnection() {
        LOGGER.log(Level.INFO,"Подключение к базе данных");
        //читаем свойства для подключения к db
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            inputStream = Files.newInputStream(Paths.get("database.properties"));
            properties.load(inputStream);
            LOGGER.log(Level.INFO, "Свойства подключения считаны в файл");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName( properties.getProperty("driver")).getDeclaredConstructor().newInstance();
            LOGGER.log(Level.INFO, "Объект драйвера создан");
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Объект драйвера не создан");
        }

        Connection connection;
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
            LOGGER.log(Level.INFO, "Соединение установлено");
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Соединение не установлено");
            throw new RuntimeException(e);
        }
        return connection;
    }
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.log(Level.INFO, "Соединение закрыто");
            } catch (SQLException e) {
                LOGGER.log(Level.INFO, "Ошибка закрытия соединения");
                e.printStackTrace();
            }
        }
    }
}
