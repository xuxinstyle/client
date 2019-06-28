package com.game.scence.model;

/**
 * @Author：xuxin
 * @Date: 2019/6/11 10:35
 */
public class PlayerPosition {
    private int x;
    private int y;

    public static PlayerPosition valueOf(int x, int y){
        PlayerPosition playerPosition = new PlayerPosition();
        playerPosition.setX(x);
        playerPosition.setY(y);
        return playerPosition;
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
        return "PlayerPosition{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
