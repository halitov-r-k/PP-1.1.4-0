package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    static private final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());
    // UserDao userDao = new UserDaoJDBCImpl();
    UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        LOGGER.log(Level.INFO, "Создание таблицы");
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        LOGGER.log(Level.INFO, "добавление записи в  таблицу users");
        userDao.saveUser(name, lastName, age);}

    public void removeUserById(long id) {userDao.removeUserById(id);  }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() { userDao.cleanUsersTable(); }
}
