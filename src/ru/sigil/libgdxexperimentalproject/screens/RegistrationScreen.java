package ru.sigil.libgdxexperimentalproject.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import ru.sigil.libgdxexperimentalproject.MyGame;
import ru.sigil.libgdxexperimentalproject.userinterface.RegistrationHUD;
import ru.sigil.libgdxexperimentalproject.view.RegistrationRenderer;


public class RegistrationScreen implements Screen, InputProcessor {
    private RegistrationRenderer renderer;
    private MyGame myGame;

    public RegistrationScreen(MyGame myGame) {
        this.myGame = myGame;
        Gdx.app.log("MyLibGDXGame", "MainMenu.create()");
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i2, int i3, int i4) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i2, int i3) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i2) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

    @Override
    public void render(float v) {
        // Тут рендерим
        Gdx.graphics.getGL20().glClearColor(0.52734375f, 0.31640625f, 0.11328125f, 1);
        Gdx.graphics.getGL20().glClear(
                GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        renderer.render();
    }

    @Override
    public void resize(int i, int i2) {

    }

    @Override
    public void show() {
        RegistrationHUD registrationHUD = new RegistrationHUD(myGame);
        renderer = new RegistrationRenderer(registrationHUD);
        Gdx.input.setInputProcessor(registrationHUD.getStage());
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
}
