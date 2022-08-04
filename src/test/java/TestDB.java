import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static jm.task.core.jdbc.util.Util.closeConnection;
import static jm.task.core.jdbc.util.Util.getConnection;

public class TestDB {
    public static void main(String[] args) {
        System.out.println("test db");

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT NOW()");
            resultSet.next();
            String data = resultSet.getString(1);
            System.out.println(data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        closeConnection(connection);
    }
}
