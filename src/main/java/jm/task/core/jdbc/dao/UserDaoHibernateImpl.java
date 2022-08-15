package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoHibernateImpl implements UserDao {
    static Logger LOGGER = Logger.getLogger(UserDaoHibernateImpl.class.getName());
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        LOGGER.log(Level.INFO, "Создание таблицы user");
        Session session = Util.getSessionFactory().openSession();
        //Transaction tx1 = session.beginTransaction();
        //tx1.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        LOGGER.log(Level.INFO, "Удаление таблицы user");
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        NativeQuery<User> nativeQuery = session.createNativeQuery("DROP TABLE IF EXISTS users", User.class);
        nativeQuery.executeUpdate();
        tx1.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        LOGGER.log(Level.INFO, "Добавление записи в таблицу user");
        User user = new User(name, lastName, age);
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(user);
        tx1.commit();
        session.close();
    }


    @Override
    public void removeUserById(long id) {
        LOGGER.log(Level.INFO, "Удаление записи из таблицы user по Id");
        String requestSQL = "DELETE FROM users WHERE Id = " + id;
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        NativeQuery<User> nativeQuery = session.createNativeQuery(requestSQL, User.class);
        nativeQuery.executeUpdate();
        tx1.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.log(Level.INFO, "Получение всех записей из таблицы user");
        List<User> users = new ArrayList<>();
        String requestSQL = "SELECT * FROM users";
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery(requestSQL, User.class);
        users = nativeQuery.getResultList();
        tx1.commit();
        session.close();
        User[] usersArray = users.toArray(new User[users.size()]);
        for(int i = 0; i < usersArray.length; i++) {
            System.out.println(usersArray[i]);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        LOGGER.log(Level.INFO, "Очистка таблицы user");
    }

}
