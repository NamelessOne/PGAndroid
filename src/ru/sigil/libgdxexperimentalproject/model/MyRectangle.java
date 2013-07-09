package ru.sigil.libgdxexperimentalproject.model;

import com.badlogic.gdx.graphics.Color;

public class MyRectangle extends MyFigure {
    private float oldX;
    private float oldY;
    private float rotation;
    private float width;
    private float x2;
    private float y2;

    public MyRectangle(int diameter, float oldX, float oldY, float X, float Y,
                       Color color) {
        super(diameter, X, Y, color);
        this.setOldX(oldX);
        this.setOldY(oldY);
        this.rotation = (float) (Math.atan2(getY() - getOldY(), getX()
                - getOldX()) * 180 / Math.PI);
        this.width = (float) Math.sqrt(Math.pow(getOldX() - getX(), 2)
                + Math.pow(getOldY() - getY(), 2));
        this.setX2(getOldX() + (getDiameter() / 2) * (getY() - getOldY())
                / getWidth());
        this.setY2(getOldY() - (getDiameter() / 2) * (getX() - getOldX())
                / getWidth());
    }

    float getOldX() {
        return oldX;
    }

    void setOldX(float oldX) {
        this.oldX = oldX;
    }

    float getOldY() {
        return oldY;
    }

    void setOldY(float oldY) {
        this.oldY = oldY;
    }

    public float getRotation() {
        return rotation;
    }

    public float getWidth() {
        return width;
    }

    public float getX2() {
        return x2;
    }

    void setX2(float x2) {
        this.x2 = x2;
    }

    public float getY2() {
        return y2;
    }

    void setY2(float y2) {
        this.y2 = y2;
    }
}