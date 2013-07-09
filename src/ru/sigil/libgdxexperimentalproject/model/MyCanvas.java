package ru.sigil.libgdxexperimentalproject.model;

import java.util.ArrayList;
import java.util.List;

public class MyCanvas {
    private List<MyRectangle> rectanglesPool = new ArrayList<MyRectangle>();// Кружочки
    private List<MyRound> roundsPool = new ArrayList<MyRound>();// Прямоугольнички
    // ширина мира
    public int width;
    // высота мира
    public int height;

    public MyCanvas() {
    }

    public List<MyRectangle> getRectanglesPool() {
        return rectanglesPool;
    }

    public List<MyRound> getRoundsPool() {
        return roundsPool;
    }
}