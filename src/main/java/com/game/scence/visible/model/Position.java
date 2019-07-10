package com.game.scence.visible.model;

/**
 * @Author：xuxin
 * @Date: 2019/6/11 10:35
 */
public class Position {
    private int x;
    private int y;

    public static Position valueOf(int x, int y){
        Position position = new Position();
        position.setX(x);
        position.setY(y);
        return position;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
