import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static jm.task.core.jdbc.util.Util.getSession;

public class Test1 {
    public static void main(String[] args) {
        SessionFactory sessionFactory = getSession();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User();
        user.setName("UserName1");
        user.setLastName("UserLastName1");
        user.setAge((byte)99);

session.save(user);
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
