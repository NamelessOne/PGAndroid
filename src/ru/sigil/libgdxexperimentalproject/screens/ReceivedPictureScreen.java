package ru.sigil.libgdxexperimentalproject.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import ru.sigil.libgdxexperimentalproject.MyGame;
import ru.sigil.libgdxexperimentalproject.controller.CanvasController;
import ru.sigil.libgdxexperimentalproject.model.MyCanvas;
import ru.sigil.libgdxexperimentalproject.networking.NetworkController;
import ru.sigil.libgdxexperimentalproject.userinterface.CanvasHUD;
import ru.sigil.libgdxexperimentalproject.userinterface.ReceivedPictureHUD;
import ru.sigil.libgdxexperimentalproject.view.CanvasRenderer;
import ru.sigil.libgdxexperimentalproject.view.ReceivedPictureRenderer;

public class ReceivedPictureScreen implements Screen, InputProcessor{
    private ReceivedPictureRenderer renderer;
    private NetworkController networkController;

    public ReceivedPictureScreen(MyGame myGame, NetworkController networkController) {
        this.networkController = networkController;
        Gdx.app.log("MyLibGDXGame", "Game.create()");
    }

    @Override
    public void show() {
        ReceivedPictureHUD receivedPictureHUD = new ReceivedPictureHUD(networkController);
        MyCanvas myCanvas = new MyCanvas();
        renderer = new ReceivedPictureRenderer(receivedPictureHUD);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    public boolean touchMoved(int x, int y) {
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public void render(float delta) {
        // Тут рендерим
        Gdx.graphics.getGL20().glClearColor(1, 1, 1, 1);
        Gdx.graphics.getGL20().glClear(
                GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        renderer.render();
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return Gdx.app.getType().equals(Application.ApplicationType.Android);
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
