package me.vallezw.MineDexPlugins.utils;

import me.vallezw.MineDexPlugins.mysql.DBConnection;

import java.sql.SQLException;
import java.util.List;

public class CheckPos {
    public boolean checksquare(Position playerPos, String username) throws SQLException, ClassNotFoundException {
        List<Integer> array = DBConnection.getIds(username);
        Position p1 = DBConnection.getPos(array.get(0));
        Position p2 = DBConnection.getPos(array.get(1));
        return playerPos.checkIfInSquare(p1, p2);
    }
}
