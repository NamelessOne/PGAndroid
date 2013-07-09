package ru.sigil.libgdxexperimentalproject.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import ru.sigil.libgdxexperimentalproject.userinterface.LoginHUD;

public class LoginRenderer extends Renderer {
    private LoginHUD loginHUD;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    public LoginRenderer(LoginHUD mainMenuHUD) {
        this.loginHUD = mainMenuHUD;
        this.cam = new OrthographicCamera(getCAMERA_WIDTH(), getCAMERA_HEIGHT());
        // устанавливаем камеру по центру
        SetCamera(getCAMERA_WIDTH() / 2f, getCAMERA_HEIGHT() / 2f);
    }

    public void render() {
        //Рисуем прямоугольник под кнопкой "регистрация"
        shapeRenderer.begin(ShapeRenderer.ShapeType.FilledRectangle);
        shapeRenderer.setColor(0.44140625f, 0.2734375f, 0.109375f, 1);//#71461C
        shapeRenderer.identity();
        shapeRenderer.translate(0, 0, 0);
        shapeRenderer.filledRect(0, 0, getSCREEN_WIDTH(),
                getSCREEN_HEIGHT() * 7 / 45);
        shapeRenderer.end();
        //Отрисовка интерфейса
        loginHUD.getStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        loginHUD.getStage().draw();
    }
}
