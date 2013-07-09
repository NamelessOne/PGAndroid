package ru.sigil.libgdxexperimentalproject.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import ru.sigil.libgdxexperimentalproject.userinterface.RegistrationHUD;

public class RegistrationRenderer extends Renderer {
    private RegistrationHUD registrationHUD;

    public RegistrationRenderer(RegistrationHUD mainMenuHUD) {
        this.registrationHUD = mainMenuHUD;
        this.cam = new OrthographicCamera(getCAMERA_WIDTH(), getCAMERA_HEIGHT());
        // устанавливаем камеру по центру
        SetCamera(getCAMERA_WIDTH() / 2f, getCAMERA_HEIGHT() / 2f);
    }

    public void render() {
        registrationHUD.getStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        registrationHUD.getStage().draw();
    }
}
