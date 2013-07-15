package ru.sigil.libgdxexperimentalproject.userinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import ru.sigil.libgdxexperimentalproject.MyGame;
import ru.sigil.libgdxexperimentalproject.bd.WordsBDAdapter;
import ru.sigil.libgdxexperimentalproject.networking.NetworkController;


public abstract class HUD {
    private MyGame myGame;
    private Dialog difficultyDialog;
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

    private MyGame getMyGame() {
        return myGame;
    }

    protected void setMyGame(MyGame myGame) {
        this.myGame = myGame;
    }

    public void showDifficultyDialog() //Диалог выбора сложности
    {
        TextButton hardButton = new TextButton("Сложно", getPGSkin());
        hardButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                String s = WordsBDAdapter.getInstance().getWord(NetworkController.DIFFICULTY_EASY);
                setCanvasScreen();
                return true;
            }
        });
        TextButton mediumButton = new TextButton("Средне", getPGSkin());
        mediumButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                String s = WordsBDAdapter.getInstance().getWord(NetworkController.DIFFICULTY_MEDIUM);
                setCanvasScreen();
                return true;
            }
        });
        TextButton easyButton = new TextButton("Легко", getPGSkin());
        easyButton.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                String s =  WordsBDAdapter.getInstance().getWord(NetworkController.DIFFICULTY_HARD);
                setCanvasScreen();
                return true;
            }
        });
        setDifficultyDialog(new Dialog("Выберите сложность", getSkin()));
        getDifficultyDialog().align(Align.center);
        getDifficultyDialog().setHeight(getStage().getHeight() / 2);
        getDifficultyDialog().setWidth(getStage().getWidth() / 2);
        getDifficultyDialog().row().fill().expandX();
        getDifficultyDialog().add(hardButton);
        getDifficultyDialog().row().fill().expandX();
        getDifficultyDialog().add(mediumButton);
        getDifficultyDialog().row().fill().expandX();
        getDifficultyDialog().add(easyButton);
        getStage().addActor(getDifficultyDialog());
    }

    //-------------CHANGE SCREENS--------------------
   protected void setCanvasScreen() {
        getMyGame().setCanvasScreen();
    }

    protected void setLoginScreen() {
        getMyGame().setLoginScreen();
    }

    protected void setRegistrationScreen() {
        getMyGame().setRegistrationScreen();
    }

    //---------------------------------
    protected Dialog getDifficultyDialog() {
        return difficultyDialog;
    }

    protected void setDifficultyDialog(Dialog difficultyDialog) {
        this.difficultyDialog = difficultyDialog;
    }
}
