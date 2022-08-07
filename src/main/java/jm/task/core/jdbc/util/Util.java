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
    private final Connection connection = createConnection();
    public Connection getConnection() {return connection;}
    private Connection createConnection() {
        LOGGER.log(Level.INFO,"Метод getConnection(): Подключение к базе данных");

        Properties properties = new Properties();
        InputStream inputStream;
        try {
            inputStream = Files.newInputStream(Paths.get("database.properties"));
            properties.load(inputStream);
            LOGGER.log(Level.INFO, "Свойства подключения считаны в properties-object");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Class.forName( properties.getProperty("driver")).getDeclaredConstructor().newInstance();
            LOGGER.log(Level.INFO, "Путь к драйверу установлен");
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Ошибка установления пути к драйверу");
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

    public void closeConnection() {
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
