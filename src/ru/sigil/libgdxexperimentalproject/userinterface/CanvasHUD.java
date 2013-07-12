package ru.sigil.libgdxexperimentalproject.userinterface;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.ArrayList;

import ru.sigil.libgdxexperimentalproject.model.ColorsSelectBox;
import ru.sigil.libgdxexperimentalproject.model.PaintBrush;
import ru.sigil.libgdxexperimentalproject.networking.NetworkController;

public class CanvasHUD extends HUD {
    //private NetworkController networkController = new NetworkController();

    public CanvasHUD() {
        Slider slider = new Slider(2, 42, 2, false, getSkin());
        slider.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                PaintBrush.setCircleDiameter((int) ((Slider) actor).getValue());
            }
        });
        slider.setValue(PaintBrush.getCircleDiameter());
        Table table = new Table();
        Table bottomTable = new Table();
        ColorsSelectBox dropDown = new ColorsSelectBox(getSkin(),
                (ArrayList<Color>) PaintBrush.getColors());
        dropDown.setSelection(PaintBrush.getCurrentColor());
        dropDown.setSelection(PaintBrush.getCurrentColor());
        Label bottomLabel = new Label("BIG RED DICK", getPGSkin());
        table.align(Align.top);
        table.setFillParent(true);
        table.add(slider).width(getStage().getWidth() * 3 / 4).height(getStage().getHeight() / 10).fill();
        table.add(dropDown).expandX().height(getStage().getHeight() / 10).fill();
        //-----------------TEST
        TextButton testButton = new TextButton("Test", getSkin());
        testButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //networkController.connectAfterLogin();
                return true;
            }
        });
        table.add(testButton).expandX().height(getStage().getHeight() / 10).fill();
        //----------------------
        table.row();
        getStage().addActor(table);
        bottomLabel.setAlignment(Align.center, Align.center);
        bottomTable.align(Align.bottom);
        bottomTable.setFillParent(true);
        bottomTable.add(bottomLabel).expandX().height(getStage().getHeight() / 10).fill();
        getStage().addActor(bottomTable);
    }
}
