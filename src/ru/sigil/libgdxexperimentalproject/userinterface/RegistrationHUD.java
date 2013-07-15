package ru.sigil.libgdxexperimentalproject.userinterface;

import android.util.Log;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URLEncoder;

import ru.sigil.libgdxexperimentalproject.MyGame;

public class RegistrationHUD extends HUD {

    private TextField emailTextField;
    private TextField loginTextField;
    private TextField passwordTextField;
    private TextField confirmPasswordTextField;

    public RegistrationHUD(MyGame myGame) {
        setMyGame(myGame);
        Label emailLabel = new Label("E-mail", getPGSkin());
        Label loginLabel = new Label("Логин", getPGSkin());
        Label passwordLabel = new Label("Пароль", getPGSkin());
        Label confirmPasswordLabel = new Label("Пароль еще раз", getPGSkin());
        emailTextField = new TextField("", getPGSkin());
        loginTextField = new TextField("", getPGSkin());
        passwordTextField = new TextField("", getPGSkin());
        confirmPasswordTextField = new TextField("", getPGSkin());
        TextButton doneButton = new TextButton("Готово", getPGSkin());
        doneButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO написать все проверки
                //setCanvasScreen();

                if (passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
                    HTTPPost();
                    setLoginScreen();
                } else {
                    //TODO сообщение про то что пароли не совпадают
                }

                return true;
            }
        });
        Table table = new Table();
        table.align(Align.center);
        table.setFillParent(true);
        table.add(emailLabel).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(0);
        table.row();
        table.add(emailTextField).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(10);
        table.row();
        table.add(loginLabel).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(0);
        table.row();
        table.add(loginTextField).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(10);
        table.row();
        table.add(passwordLabel).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(0);
        table.row();
        table.add(passwordTextField).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(10);
        table.row();
        table.add(confirmPasswordLabel).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(0);
        table.row();
        table.add(confirmPasswordTextField).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(10);
        table.row();
        table.add(doneButton).width(getStage().getWidth() * 11 / 12).height(getStage().getHeight() / 12).fill().padBottom(10);
        getStage().addActor(table);
        setHeader("Регистрация");
    }

    private void HTTPPost() {
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet("http://pgdb3-sigil.rhcloud.com/registration?login=" +
                    URLEncoder.encode(loginTextField.getText(), "utf-8") +
                    "&email=" + URLEncoder.encode(emailTextField.getText(), "utf-8") +
                    "&password=" + URLEncoder.encode(passwordTextField.getText(), "utf-8"));
            httpclient.execute(httpget);
        } catch (Exception e) {
            Log.v("!!!!!!", "jiodisj");
            e.printStackTrace();
        }
    }
}
