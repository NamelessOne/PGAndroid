package ru.sigil.libgdxexperimentalproject.userinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public abstract class HUD {
    private static Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
    private static Skin pgSkin = new Skin(Gdx.files.internal("data/pgskin.json"));
    private Stage stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
            false);

    public Stage getStage() {
        return stage;
    }

    Skin getSkin() {
        return skin;
    }

    Skin getPGSkin() {
        return pgSkin;
    }

    void setHeader(String label) {
        Label headerLabel = new Label(label, getPGSkin(), "header");
        headerLabel.setAlignment(Align.center);
        Table tableHeader = new Table();
        tableHeader.align(Align.top);
        tableHeader.setFillParent(true);
        tableHeader.add(headerLabel).width(getStage().getWidth()).height(getStage().getHeight() * 4 / 45).fill().padBottom(0).top();
        tableHeader.row();
        getStage().addActor(tableHeader);
    }
}
