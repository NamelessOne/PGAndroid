package ru.sigil.libgdxexperimentalproject.userinterface;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import ru.sigil.libgdxexperimentalproject.MyGame;

public class MainMenuHUD extends HUD {

    public MainMenuHUD(MyGame myGame) {
        setMyGame(myGame);
        TextButton newGameButton = new TextButton("Новая игра", getPGSkin());
        newGameButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setLoginScreen();
                return true;
            }
        });
        TextButton achievementsButton = new TextButton("Достижения", getPGSkin());
        achievementsButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("down");
                return true;
            }
        });
        Table table = new Table();
        table.align(Align.top);
        table.padTop(getStage().getHeight() * 13 / 36);
        table.setFillParent(true);
        table.add(newGameButton).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(getStage().getHeight() / 10);
        table.row();
        table.add(achievementsButton).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill();
        getStage().addActor(table);
        setHeader("GAME");
    }
}
