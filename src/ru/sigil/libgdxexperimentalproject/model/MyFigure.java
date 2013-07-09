package ru.sigil.libgdxexperimentalproject.model;

import com.badlogic.gdx.graphics.Color;

public abstract class MyFigure {

    private int diameter;
    private float X;
    private float Y;
    private Color color;

    MyFigure(int diameter, float X, float Y, Color color) {
        this.diameter = diameter;
        this.X = X;
        this.Y = Y;
        this.color = color;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public float getX() {
        return X;
    }

    public void setX(float x) {
        X = x;
    }

    public float getY() {
        return Y;
    }

    public void setY(float y) {
        Y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
