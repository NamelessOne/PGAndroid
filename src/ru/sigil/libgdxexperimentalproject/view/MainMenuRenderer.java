package ru.sigil.libgdxexperimentalproject.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import ru.sigil.libgdxexperimentalproject.userinterface.MainMenuHUD;

public class MainMenuRenderer extends Renderer {
    private MainMenuHUD mainMenuHUD;

    public MainMenuRenderer(MainMenuHUD mainMenuHUD) {
        this.mainMenuHUD = mainMenuHUD;
        this.cam = new OrthographicCamera(getCAMERA_WIDTH(), getCAMERA_HEIGHT());
        // устанавливаем камеру по центру
        SetCamera(getCAMERA_WIDTH() / 2f, getCAMERA_HEIGHT() / 2f);
    }

    public void render() {
        mainMenuHUD.getStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        mainMenuHUD.getStage().draw();
    }
}
