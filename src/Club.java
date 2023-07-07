import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class Club {
    long id;
    String club_name;
    long manager_id;
    boolean new_record;

    public Club() {
        new_record = true;
    }

    public Club(String name, long m_id) {
        new_record = true;
        this.club_name = name;
        this.manager_id = m_id;
    }

    public Club(long id, String name, long m_id) {
        new_record = true;
        this.id = id;
        this.club_name = name;
        this.manager_id = m_id;
    }

    public static Club find(long id) {
        String query = "SELECT * FROM clubs WHERE id='" + id + "';";
        Club c = new Club();

        try (
                PreparedStatement ps = Db.conn().prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            rs.next();

            c.id = rs.getLong("id");
            c.club_name = rs.getString("club_name");
            c.manager_id = rs.getLong("email");
            c.new_record = false;

        } catch (SQLException e) {
            return null;
        }

        return c;
    }

    public static ArrayList<Club> findall() {
        String query = "SELECT * FROM clubs;";
        ArrayList<Club> clubs = new ArrayList<Club>();
        try (
                PreparedStatement ps = Db.conn().prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Club c = new Club();
                c.id = rs.getLong("id");
                c.club_name = rs.getString("name");
                c.manager_id = rs.getLong("email");
                c.new_record = false;
                clubs.add(c);
            }
        } catch (SQLException e) {
            return null;
        }

        return clubs;
    }

    public boolean save() {
        return new_record ? insertRecord() : updateRecord();
    }

    private boolean insertRecord() {
        try {
            Statement st = Db.conn().createStatement();
            int result = st.executeUpdate("INSERT INTO clubs(club_name, manager_id) VALUES('" + club_name +
                    "', '" + manager_id + "');", RETURN_GENERATED_KEYS);
            new_record = false;

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
            String query = "UPDATE clubs SET club_name='" + club_name + "', manager_id='" + manager_id +
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
            String query = "DELETE FROM clubs WHERE id = '" + this.id + "';";
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
