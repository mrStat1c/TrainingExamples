import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%s?user=%s&password=%s",
                    "localhost",
                    "3306",
                    "root",
                    "root")
            );

        } catch (Exception e) {
            System.out.println("Ошибка при подключении к бд:\n" + e.getMessage());
        }

        Db db = new Db(connection);
        System.out.println(String.format("In the system %s cards in the status of silver",
                db.getRecordCount("dictionaries.cards", Collections.singletonMap("type", "Silver"))));
    }
}
