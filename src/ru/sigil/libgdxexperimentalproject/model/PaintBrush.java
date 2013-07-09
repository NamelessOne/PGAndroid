package ru.sigil.libgdxexperimentalproject.model;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class PaintBrush {
    private static ArrayList<Color> colors = new ArrayList<Color>();
    private static Color currentColor;
    private static int circleDiameter = 20;//По умолчанию

    public static void setColors() {
        colors.clear();
        colors.add(Color.BLACK);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.WHITE);
        colors.add(Color.PINK);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.BLUE);
        currentColor = colors.get(0);
    }


    public static Color getCurrentColor() {
        return currentColor;
    }

    public static void setCurrentColor(Color currentColor) {
        PaintBrush.currentColor = currentColor;
    }

    public static int getCircleDiameter() {
        return circleDiameter;
    }

    public static void setCircleDiameter(int circleDiameter) {
        PaintBrush.circleDiameter = circleDiameter;
    }

    public static List<Color> getColors() {
        if (colors == null)
            setColors();
        return colors;
    }
}
