package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    static Logger LOGGER = Logger.getLogger(UserDaoJDBCImpl.class.getName());
    private final Util util = new Util();
    private final Connection connection = util.getConnection();

    public void closeConnection () {
        util.closeConnection();
    }

     public void createUsersTable() {
        LOGGER.log(Level.INFO,"Create Table Users ");
         String requestSQL = "CREATE TABLE IF NOT EXISTS users (Id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastname VARCHAR(50), age TINYINT)";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(requestSQL);
            LOGGER.log(Level.INFO,"Таблица users создана ");
        } catch (SQLException e) {
            LOGGER.log(Level.INFO,"Ошибка создания таблицы users");
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        LOGGER.log(Level.INFO,"Drop Table Users ");
        String requestSQL = "DROP TABLE IF EXISTS users";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(requestSQL);
            LOGGER.log(Level.INFO,"Таблица users  удалена");
        } catch (SQLException e) {
            LOGGER.log(Level.INFO,"Ошибка удаления таблицы users ");
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
       LOGGER.log(Level.INFO,"Save User ");
        try {
            String requestSQL = "INSERT users(name, lastname, age) VALUES(?, ?, ?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(requestSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных \n", name);
        } catch (SQLException e) {
            LOGGER.log(Level.INFO,"Ошибка Добавления записи");
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        LOGGER.log(Level.INFO,"Удаление user по id");
        try {
            String requestSQL = "DELETE FROM users WHERE Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(requestSQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            LOGGER.log(Level.INFO,"user c id {0} удален из таблицы", id);
        } catch (SQLException e) {
            LOGGER.log(Level.INFO,"Ошибка удаления записи");
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        LOGGER.log(Level.INFO,"Получение всех записей из таблицы users ");
        List<User> users = new ArrayList<>();
        String requestSQL = "SELECT * FROM users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(requestSQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
                System.out.println(user);
            }
            LOGGER.log(Level.INFO,"Все записи получены");
        } catch (SQLException e) {
            LOGGER.log(Level.INFO,"Ошибка получения всех записей ");
            throw new RuntimeException(e);
        }

        return users;
    }
    public void cleanUsersTable() {
        LOGGER.log(Level.INFO,"Clean Table Users ");
        String requestSQL = "TRUNCATE TABLE users";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(requestSQL);
            LOGGER.log(Level.INFO,"Таблица users очищена ");
        } catch (SQLException e) {
            LOGGER.log(Level.INFO,"Ошибка очистки таблицы users");
            throw new RuntimeException(e);
        }
    }
}
