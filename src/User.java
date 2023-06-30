import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class User {
    long id;
    String name;
    String email;
    boolean new_record;

    public User() {
        new_record = true;
    }

    public User(String name, String email) {
        new_record = true;
        this.name = name;
        this.email = email;
    }

    public User(long id, String name, String email) {
        new_record = true;
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static User find(long id) {
        String query = "SELECT * FROM users WHERE id='" + id + "';";
        User u = new User();

        try (
            PreparedStatement ps = Db.conn().prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {
            rs.next();

            u.id = rs.getLong("id");
            u.name = rs.getString("name");
            u.email = rs.getString("email");
            u.new_record = false;

        } catch (SQLException e) {
            return null;
        }

        return u;
    }

    public static ArrayList<User> findall() {
        String query = "SELECT * FROM users;";
        ArrayList<User> users = new ArrayList<User>();
        try (
            PreparedStatement ps = Db.conn().prepareStatement(query);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.id = rs.getLong("id");
                u.name = rs.getString("name");
                u.email = rs.getString("email");
                u.new_record = false;
                users.add(u);
            }
        } catch (SQLException e) {
            return null;
        }

        return users;
    }

    public boolean save() {
        return new_record ? insertRecord() : updateRecord();
    }

    private boolean insertRecord() {
        try {
            Statement st = Db.conn().createStatement();
            int result = st.executeUpdate("INSERT INTO users(name, email) VALUES('" + name +
                                              "', '" + email + "');", RETURN_GENERATED_KEYS);
            new_record = false;

            System.out.println("Result = " + result);
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            id = rs.getLong(1);
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

    private boolean updateRecord() {
        try {
            Statement st = Db.conn().createStatement();
            String query = "UPDATE users SET name='" + name + "', email='" + email +
                           "' WHERE id=" + id + ";";
            st.executeUpdate(query);
        }
        catch (Exception e) {
            System.err.println("!!Exception: ");
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean delete() {
        int result;
        try {
            Statement st = Db.conn().createStatement();
            String query = "DELETE FROM users WHERE id = '" + this.id + "';";
            result = st.executeUpdate(query);
        }
        catch (Exception e) {
            System.err.println("!!Exception: ");
            System.out.println(e.getMessage());
            return false;
        }
        return result > 0;
    }
}
