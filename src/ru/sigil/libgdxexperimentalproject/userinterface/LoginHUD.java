package ru.sigil.libgdxexperimentalproject.userinterface;

import android.util.Log;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;

import ru.sigil.libgdxexperimentalproject.MyGame;
import ru.sigil.libgdxexperimentalproject.bd.WordsBDAdapter;
import ru.sigil.libgdxexperimentalproject.model.Player;
import ru.sigil.libgdxexperimentalproject.networking.NetworkController;

public class LoginHUD extends HUD {
    Dialog difficultyDialog;
    private MyGame myGame;
    private TextField loginTextField;
    private TextField passwordTextField;


    public LoginHUD(MyGame myGame) {
        this.myGame = myGame;
        Label loginLabel = new Label("Логин", getPGSkin());
        Label passwordLabel = new Label("Пароль", getPGSkin());
        loginTextField = new TextField("", getPGSkin());
        passwordTextField = new TextField("", getPGSkin());
        TextButton doneButton = new TextButton("Готово", getPGSkin());
        doneButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO написать все проверки
                //setCanvasScreen();
                int id = HTTPGet();
                if (id > 0) {  //Залогинились успешно.
                    Player.init(loginTextField.getText(), passwordTextField.getText(), id);
                    //TODO коннектимся к серверу, получаем роль и выбираем сложность.
                    //TODO Пока реализуем диалог с выбором сложности
                    showWaitingWindow("Подождите");
                    //TODO Запускаем NetworkController.(Подумать как). Логинимся.
                    NetworkController networkController = new NetworkController(getWaitingDialog());
                    networkController.start();
                    Log.v("222222", "fsfsa");
                    //showChooseDifficultyDialog();
                } else {
                    //TODO сообщение о том, что неправильная пара логин/пароль
                }
                return true;
            }
        });
        TextButton registrationButton = new TextButton("Регистрация", getPGSkin());
        registrationButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setRegistrationScreen();
                return true;
            }
        });
        Table bottomTable = new Table();
        bottomTable.align(Align.bottom);
        bottomTable.setFillParent(true);
        bottomTable.add(loginLabel).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(0);
        bottomTable.row();
        bottomTable.add(loginTextField).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(10);
        bottomTable.row();
        bottomTable.add(passwordLabel).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(0);
        bottomTable.row();
        bottomTable.add(passwordTextField).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(getStage().getHeight() * 13 / 180);
        bottomTable.row();
        bottomTable.add(doneButton).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(getStage().getHeight() * 5 / 18);
        bottomTable.row();
        bottomTable.add(registrationButton).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(getStage().getHeight() / 30);
        getStage().addActor(bottomTable);
        setHeader("Войти");
    }

    private void setCanvasScreen() {
        myGame.setCanvasScreen();
    }

    private void setRegistrationScreen() {
        myGame.setRegistrationScreen();
    }

    private int HTTPGet() {
        int id = 0;
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet("http://pgdb3-sigil.rhcloud.com/login?login=" +
                    URLEncoder.encode(loginTextField.getText(), "utf-8") +
                    "&password=" + URLEncoder.encode(passwordTextField.getText(), "utf-8"));
            HttpResponse response = httpclient.execute(httpget);
            String s = EntityUtils.toString(response.getEntity());
            id = Integer.valueOf(s.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private void showChooseDifficultyDialog() //Диалог выбора сложности
    {
        TextButton hardButton = new TextButton("Сложно", getPGSkin());
        hardButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                WordsBDAdapter.getInstance().getWord(NetworkController.DIFFICULTY_EASY);
                setCanvasScreen();
                return true;
            }
        });
        TextButton mediumButton = new TextButton("Средне", getPGSkin());
        mediumButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                WordsBDAdapter.getInstance().getWord(NetworkController.DIFFICULTY_MEDIUM);
                setCanvasScreen();
                return true;
            }
        });
        TextButton easyButton = new TextButton("Легко", getPGSkin());
        easyButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                WordsBDAdapter.getInstance().getWord(NetworkController.DIFFICULTY_HARD);
                setCanvasScreen();
                return true;
            }
        });
        difficultyDialog = new Dialog("Выберите сложность", getSkin());
        difficultyDialog.align(Align.center);
        difficultyDialog.setHeight(getStage().getHeight() / 2);
        difficultyDialog.setWidth(getStage().getWidth() / 2);
        difficultyDialog.row().fill().expandX();
        difficultyDialog.add(hardButton);
        difficultyDialog.row().fill().expandX();
        difficultyDialog.add(mediumButton);
        difficultyDialog.row().fill().expandX();
        difficultyDialog.add(easyButton);
        getStage().addActor(difficultyDialog);
    }
}
