package ru.sigil.libgdxexperimentalproject.userinterface;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;


public abstract class HUD {
    private static Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
    private static Skin pgSkin = new Skin(Gdx.files.internal("data/pgskin.json"));
    private Stage stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
            false);
    private Dialog waitingDialog;


    public static Skin getPgSkin() {
        return pgSkin;
    }


    public Stage getStage() {
        return stage;
    }

    Skin getSkin() {
        return skin;
    }

    Skin getPGSkin() {
        return getPgSkin();
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

    void showWaitingWindow(String header)
    {
        waitingDialog = new Dialog(header, getSkin());
        getWaitingDialog().align(Align.center);
        getWaitingDialog().setHeight(getStage().getHeight() / 2);
        getWaitingDialog().setWidth(getStage().getWidth() / 2);
        getWaitingDialog().row().fill().expandX();
        getStage().addActor(getWaitingDialog());
    }

    public Dialog getWaitingDialog() {
        return waitingDialog;
    }
}
