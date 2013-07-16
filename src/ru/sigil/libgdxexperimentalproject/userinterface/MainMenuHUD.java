package ru.sigil.libgdxexperimentalproject.userinterface;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import ru.sigil.libgdxexperimentalproject.Match;
import ru.sigil.libgdxexperimentalproject.MyGame;
import ru.sigil.libgdxexperimentalproject.model.Player;
import ru.sigil.libgdxexperimentalproject.networking.NetworkController;

public class MainMenuHUD extends HUD {

    public MainMenuHUD(MyGame myGame) {
        setMyGame(myGame);
        TextButton newGameButton = new TextButton("Новая игра", getPGSkin());
        newGameButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (Player.getInstance().getId() > 0) {
                    Match match = new Match();
                    //TODO Успено были залогинены, начинаем новую игру
                    showWaitingWindow("Подождите");
                    //TODO Запускаем NetworkController.(Подумать как). Логинимся.
                    setNetworkController(new NetworkController(getWaitingDialog(), get()));
                    getNetworkController().start();
                }
                return true;
            }
        });
        TextButton loginButton = new TextButton("Войти", getPGSkin());
        loginButton.addListener(new InputListener() {
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
        table.padTop(getStage().getHeight() * 10 / 36);
        table.setFillParent(true);
        table.add(newGameButton).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(getStage().getHeight() / 10);
        table.row();
        table.add(loginButton).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(getStage().getHeight() / 10);
        table.row();
        table.add(achievementsButton).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill();
        getStage().addActor(table);
        setHeader("GAME");
    }
}
