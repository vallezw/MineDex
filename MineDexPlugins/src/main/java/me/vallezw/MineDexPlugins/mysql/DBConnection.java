package me.vallezw.MineDexPlugins.mysql;

import me.vallezw.MineDexPlugins.utils.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS players(username VARCHAR(50), coindex int, plotid1 int, plotid2 int, primary key(username), foreign key (plotid1) references position(id), foreign key (plotid2) references position(id)) ");
        create.executeUpdate();
    }
    public static void createPositionTable() throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS position(id int PRIMARY KEY AUTO_INCREMENT, x double, y double, z double)");
        create.executeUpdate();
    }

    public static void addUser(String user) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement add = con.prepareStatement(
                "INSERT INTO players (username, coindex) VALUES('" + user + "', 0)");
        add.executeUpdate();
    }

    public static int getCoin(String username) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("SELECT coindex FROM players WHERE username = '" + username + "'" );

        ResultSet result = statement.executeQuery();
        ArrayList<Integer> array= new ArrayList<Integer>();
        while(result.next()){
            array.add(result.getInt("coindex"));
        }
        int coins = array.get(0);
        return coins;
    }

    public void payUser(String sender, String receiver, int amount) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement pay = con.prepareStatement("UPDATE players SET coindex = coindex + " + amount + " WHERE username = '" + receiver +"'");
        pay.executeUpdate();
        removeCoinsFromUser(sender, amount);
    }

    private void removeCoinsFromUser(String sender, int amount) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement rem = con.prepareStatement("UPDATE players SET coindex = coindex - " + amount + " WHERE username = '" + sender + "'");
        rem.executeUpdate();
    }

    public void addPosition(Position p) throws SQLException, ClassNotFoundException {
        double x = p.getX();
        double y = p.getY();
        double z = p.getZ();

        Connection con = getConnection();
        PreparedStatement add = con.prepareStatement("INSERT INTO players (x, y, z) VALUES(" + x + ", " + y + ", " + z +")");
        add.executeUpdate();
    }

    public static List<Integer> getIds(String username) throws SQLException, ClassNotFoundException {

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("SELECT plotid1, plotid2 from players WHERE username = " + username);
        ResultSet result = statement.executeQuery();

        List<Integer> array = new ArrayList<Integer>();
        while(result.next()){
            array.add(result.getInt("plotid1"));
            array.add(result.getInt("plotid2"));
        }
        return array;
    }

    public static Position getPos(int rowid) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement("SELECT * from position WHERE id = " + rowid);
        ResultSet result = statement.executeQuery();
        List<Integer> array = new ArrayList<Integer>();
        while(result.next()){
            array.add(result.getInt("x"));
            array.add(result.getInt("y"));
            array.add(result.getInt("z"));
        }
        return new Position(array.get(0), array.get(1), array.get(2));
    }
}
