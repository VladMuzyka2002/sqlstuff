import java.sql.*;
import java.util.Scanner;

public class SQLServer {
    String connectionUrl;
    Connection conn;

    static SQLServer instance = null;

    static public SQLServer getInstance() {
        if (instance == null) instance = new SQLServer();
        return instance;
    }

    private SQLServer(){
        try {
            Class cls = Class.forName("com.mysql.cj.jdbc.Driver");
            this.connectionUrl = "jdbc:mysql://localhost:3306/clubs?serverTimezone=UTC";
            conn = DriverManager.getConnection(connectionUrl, "root", "Diablozaur9387!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            System.out.println("Connection was not created.\n");
        }
    }

    public void getUsers(){
        try (
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM users");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                System.out.println(name + " " + email);
                // do something with the extracted data...
            }
        } catch (SQLException e) {
            System.err.println("!!Exception: ");
            System.out.println(e.getMessage());
        }
    }

    public void getClubs(){
        try (
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM clubs");
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("club_name");
                long manager_id = rs.getLong("manager_id");

                System.out.println("ClubName: " + name + ", ManagerId: " + manager_id);
                // do something with the extracted data...
            }
        } catch (SQLException e) {
            System.err.println("!!Exception: ");
            System.out.println(e.getMessage());
        }
    }
}
