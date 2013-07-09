package ru.sigil.libgdxexperimentalproject;

import android.os.Handler;

import com.badlogic.gdx.Game;

import ru.sigil.libgdxexperimentalproject.screens.CanvasScreen;
import ru.sigil.libgdxexperimentalproject.screens.LoginScreen;
import ru.sigil.libgdxexperimentalproject.screens.MainMenuScreen;
import ru.sigil.libgdxexperimentalproject.screens.RegistrationScreen;

public class MyGame extends Game {

    private CanvasScreen canvasScreen;
    @SuppressWarnings("FieldCanBeLocal")
    private MainMenuScreen mainMenuScreen;
    private LoginScreen loginScreen;
    private RegistrationScreen registrationScreen;
    private Handler showAdsHandler;
    private final int SHOW_ADS = 1;
    private final int HIDE_ADS = 0;

    @Override
    public void create() {
        mainMenuScreen = new MainMenuScreen(this);
        canvasScreen = new CanvasScreen(this);
        loginScreen = new LoginScreen(this);
        registrationScreen = new RegistrationScreen(this);
        setScreen(mainMenuScreen);
    }

    @Override
    public void resize(int width, int height) {
    }

    public void setCanvasScreen() {
        getShowAdsHandler().sendEmptyMessage(HIDE_ADS);
        setScreen(canvasScreen);
    }

    public void setLoginScreen() {
        getShowAdsHandler().sendEmptyMessage(SHOW_ADS);
        setScreen(loginScreen);
    }

    public void setRegistrationScreen() {
        getShowAdsHandler().sendEmptyMessage(SHOW_ADS);
        setScreen(registrationScreen);
    }

    public Handler getShowAdsHandler() {
        return showAdsHandler;
    }

    public void setShowAdsHandler(Handler showAdsHandler) {
        this.showAdsHandler = showAdsHandler;
    }
}