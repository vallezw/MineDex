package me.vallezw.MineDexPlugins.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class Position {
    private final double x;
    private final double y;
    private final double z;

    public boolean checkIfInSquare(Position p1, Position p2) {
        return (x <= Math.max(p1.x, p2.x) && x >= Math.min(p1.x, p2.x))
                && (y <= Math.max(p1.y, p2.y) && y >= Math.min(p1.y, p2.y));
    }
}
