package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    static Logger LOGGER = Logger.getLogger(UserDaoJDBCImpl.class.getName());
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        LOGGER.log(Level.INFO,"Create Table Users ");
    }

    public void dropUsersTable() {
        LOGGER.log(Level.INFO,"Drop Table Users ");
    }

    public void saveUser(String name, String lastName, byte age) {
        LOGGER.log(Level.INFO,"Save User ");
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
