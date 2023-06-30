import java.sql.*;
import java.util.Scanner;

public class SQLServer {
    String connectionUrl;

    public SQLServer(){
        try {
            Class cls = Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        this.connectionUrl = "jdbc:mysql://localhost:3306/clubs?serverTimezone=UTC";


    }

    public void getUsers(){
        try (Connection conn = DriverManager.getConnection(this.connectionUrl, "root", "Diablozaur9387!");
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
            System.out.println("Connection was not created.\n");
        }
    }



    public void addUser(String userName, String userEmail)
    {
        try
        {
            Connection conn = DriverManager.getConnection(this.connectionUrl, "root", "Diablozaur9387!");

            Statement st = conn.createStatement();

            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate("INSERT INTO users(name, email) VALUES('" + userName + "', '" + userEmail + "');");

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }
}
