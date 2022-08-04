import java.sql.Connection;

import static jm.task.core.jdbc.util.Util.closeConnection;
import static jm.task.core.jdbc.util.Util.getConnection;

public class TestDB {
    public static void main(String[] args) {
        System.out.println("test db");

        Connection connection = getConnection();
        closeConnection(connection);
    }
}
