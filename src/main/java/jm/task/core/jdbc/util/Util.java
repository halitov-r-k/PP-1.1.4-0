package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
    //для jdbc
    //получаю свойства для подключения к db
    private static final Properties properties = getProperties();

    private static Properties getProperties() {
        LOGGER.log(Level.INFO, "Получение свойств db");
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/database.properties"))) {
            properties.load(inputStream);
            LOGGER.log(Level.INFO, "Свойства подключения db считаны в properties-object");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Ошибка получения свойств db: {0}", e.getMessage());
        }
        return properties;
    }

    //Подключение к db
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
            LOGGER.log(Level.INFO, "Соединение установлено");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Соединение не установлено: {0}", e.getMessage());
        }
        return connection;
    }

    // Отключение от db
    public static void close() {
        if (connection != null) {
            try {
                connection.close();
                LOGGER.log(Level.INFO, "Соединение разорвано");
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Ошибка разрыва соединения: {0}", e.getMessage());
            }
        }
    }
    // Hibernate
    //конфигурация для Hibernate

    public static SessionFactory getSession() {
        LOGGER.log(Level.INFO, "Создание SessionFactory");
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();
    }

}
