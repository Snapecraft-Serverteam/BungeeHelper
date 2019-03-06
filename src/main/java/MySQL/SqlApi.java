package MySQL;

import java.sql.*;

public class SqlApi {


    /**
     * Execute an SQL query that does not return anything.
     * @param query the query String
     */
    public static void executeSQL(String query) {
        String url = "jdbc:mysql://localhost:3306/bans?useSSL=false";
        String user = "banUser";
        String password = "";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            st.execute(query);
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     * Execute an SQL query that returns a String
     * @param query the query String
     * @return the ResultSet.
     */
    public static String executeQuery(String query) {

        String url = "jdbc:mysql://localhost:3306/bans?useSSL=false";
        String user = "banUser";
        String password = "";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "";
    }

}
