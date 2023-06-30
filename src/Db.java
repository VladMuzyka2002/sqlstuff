import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    public Connection conn;
    static Db instance = null;

    static public Connection conn() {
        if (instance == null) instance = new Db();
        return instance.conn;
    }

    private Db() {
        try {
            Class cls = Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clubs?serverTimezone=UTC",
                                              "root",
                                           "Diablozaur9387!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
