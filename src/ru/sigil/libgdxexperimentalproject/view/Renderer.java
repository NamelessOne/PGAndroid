package ru.sigil.libgdxexperimentalproject.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class Renderer {
    private static float CAMERA_WIDTH = Gdx.graphics.getWidth();
    private static float CAMERA_HEIGHT = Gdx.graphics.getHeight();
    private static int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    private int width;
    private int height;
    private float ppuX; // пикселей на точку мира по X
    private float ppuY; // пикселей на точку мира по Y

    OrthographicCamera cam;

    public static float getCAMERA_WIDTH() {
        return CAMERA_WIDTH;
    }

    public static float getCAMERA_HEIGHT() {
        return CAMERA_HEIGHT;
    }

    public static void setCAMERA_HEIGHT(float CAMERA_HEIGHT) {
        Renderer.CAMERA_HEIGHT = CAMERA_HEIGHT;
    }

    public static int getSCREEN_WIDTH() {
        return SCREEN_WIDTH;
    }

    public static int getSCREEN_HEIGHT() {
        return SCREEN_HEIGHT;
    }

    public static void setSCREEN_HEIGHT(int SCREEN_HEIGHT) {
        Renderer.SCREEN_HEIGHT = SCREEN_HEIGHT;
    }

    public void setSize(int w, int h) {
        this.setWidth(w);
        this.setHeight(h);
        setPpuX((float) getWidth() / getCAMERA_WIDTH());
        setPpuY((float) getHeight() / getCAMERA_HEIGHT());
    }

    // установка камеры
    void SetCamera(float x, float y) {
        this.cam.position.set(x, y, 0);
        this.cam.update();
    }

    int getWidth() {
        return width;
    }

    void setWidth(int width) {
        this.width = width;
    }

    int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }

    public float getPpuX() {
        return ppuX;
    }

    void setPpuX(float ppuX) {
        this.ppuX = ppuX;
    }

    public float getPpuY() {
        return ppuY;
    }

    void setPpuY(float ppuY) {
        this.ppuY = ppuY;
    }
}
