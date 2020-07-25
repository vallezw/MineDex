package me.vallezw.MineDexPlugins.mysql;

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/minedex";
        String username = "root";
        String password = "123456";
        Class.forName(driver);

        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }
    public static void createtable() throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS players(username VARCHAR(50), coindex int)");
        create.executeUpdate();
    }

    public static void addUser(String user) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement add = con.prepareStatement("INSERT INTO players (username, coindex) VALUES('" + user + "', 0)");
        add.executeUpdate();
    }

    public int getCoin(String username) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement get = con.prepareStatement("SELECT coindex from players WHERE username = '" + username + "'");
        ResultSet result = get.executeQuery();

        return result.getInt("coindex");
    }

    public void payUser(String sender, String receiver, int amount) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement pay = con.prepareStatement("UPDATE players SET coindex = coindex + " + String.valueOf(amount) + " WHERE username = '" + receiver +"'");
        pay.executeUpdate();
        removeCoinsFromUser(sender, amount);
    }

    private void removeCoinsFromUser(String sender, int amount) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement rem = con.prepareStatement("UPDATE players SET coindex = coindex - " + String.valueOf(amount) + " WHERE username = '" + sender + "'");
        rem.executeUpdate();

    }
}
