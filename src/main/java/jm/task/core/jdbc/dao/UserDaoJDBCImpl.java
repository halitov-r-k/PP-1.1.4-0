package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    static Logger LOGGER = Logger.getLogger(UserDaoJDBCImpl.class.getName());
    private final Util util = new Util();
    private final Connection connection = util.getConnection();
    public Connection getConnection() {
        return connection;
    }
    public void closeConnection () {
        util.closeConnection();
    }

     public void createUsersTable() {
        LOGGER.log(Level.INFO,"Create Table Users ");
        try {
            Statement statement = connection.createStatement();
            String requestSQL = "CREATE TABLE IF NOT EXISTS users (Id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastname VARCHAR(50), age TINYINT)";
            statement.executeUpdate(requestSQL);
            LOGGER.log(Level.INFO,"Таблица users создана ");
        } catch (SQLException e) {
            LOGGER.log(Level.INFO,"Ошибка создания таблицы users");
            throw new RuntimeException(e);
        }
    }


    public void dropUsersTable() {
        LOGGER.log(Level.INFO,"Drop Table Users ");
        try {
            Statement statement = connection.createStatement();
            String requestSQL = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(requestSQL);
            LOGGER.log(Level.INFO,"Таблица users  удалена");
        } catch (SQLException e) {
            LOGGER.log(Level.INFO,"Ошибка удаления таблицы users ");
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        LOGGER.log(Level.INFO,"Save User ");
        Connection connection = getConnection();
        try {
            String requestSQL = "INSERT users(name, lastname, age) VALUES(?, ?, ?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(requestSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных ", name);
        } catch (SQLException e) {
            LOGGER.log(Level.INFO,"Ошибка Добавления записи");
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        LOGGER.log(Level.INFO,"Remove User By Id ");
    }

    public List<User> getAllUsers() {
        LOGGER.log(Level.INFO,"Get All Users ");
        return null;
    }

    public void cleanUsersTable() {
        LOGGER.log(Level.INFO,"Clean Table Users ");
    }
}
