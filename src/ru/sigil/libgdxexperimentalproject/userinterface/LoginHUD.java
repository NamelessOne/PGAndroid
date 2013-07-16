package ru.sigil.libgdxexperimentalproject.userinterface;

import android.util.Log;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
import ru.sigil.libgdxexperimentalproject.model.Player;

public class LoginHUD extends HUD {
    private TextField loginTextField;
    private TextField passwordTextField;


    public LoginHUD(MyGame myGame) {
        this.setMyGame(myGame);
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
                    setMainMenuScreen();
                    Log.v("222222", "fsfsa");
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
}
