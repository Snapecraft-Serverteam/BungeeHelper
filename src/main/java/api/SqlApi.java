package api;

import main.BungeeHelper;

import java.sql.*;

public class SqlApi {


    /**
     * Execute an SQL query that does not return anything.
     * @param query the query String
     */
    public static void executeSQL(String query) {
        String host = BungeeHelper.getInstance().getConfiguration().getString("mysql.host");
        int port = BungeeHelper.getInstance().getConfiguration().getInt("mysql.port");;
        String user = BungeeHelper.getInstance().getConfiguration().getString("mysql.user");
        String password = BungeeHelper.getInstance().getConfiguration().getString("mysql.pw");
        String db = BungeeHelper.getInstance().getConfiguration().getString("mysql.db");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + db +"?useSSL=false";
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

        String host = BungeeHelper.getInstance().getConfiguration().getString("mysql.host");
        int port = BungeeHelper.getInstance().getConfiguration().getInt("mysql.port");;
        String user = BungeeHelper.getInstance().getConfiguration().getString("mysql.user");
        String password = BungeeHelper.getInstance().getConfiguration().getString("mysql.pw");
        String db = BungeeHelper.getInstance().getConfiguration().getString("mysql.db");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + db +"?useSSL=false";
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
