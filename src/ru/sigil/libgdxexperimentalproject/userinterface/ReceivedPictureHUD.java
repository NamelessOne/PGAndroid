package ru.sigil.libgdxexperimentalproject.userinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import ru.sigil.libgdxexperimentalproject.networking.NetworkController;


public class ReceivedPictureHUD extends HUD {
    private Pixmap rcvdPixmap;
    public ReceivedPictureHUD(NetworkController networkController) {
        //setRcvdPixmap(new Pixmap(networkController.getRecvdPicture(), 0, 0));
        //----------------------------------
        FileHandle image = Gdx.files.external("directory/rcvd.png");
        setRcvdPixmap(new Pixmap(image));
        //----------------------------------------------
        setNetworkController(networkController);
        networkController.setCurrentHud(this);
        Table table = new Table();
        Table bottomTable = new Table();
        Label bottomLabel = new Label(networkController.getKeyWord(), getPGSkin());
        table.align(Align.top);
        table.setFillParent(true);
        //----------------------
        table.row();
        getStage().addActor(table);
        bottomLabel.setAlignment(Align.center, Align.center);
        bottomTable.align(Align.bottom);
        bottomTable.setFillParent(true);
        bottomTable.add(bottomLabel).expandX().height(getStage().getHeight() / 10).fill();
        getStage().addActor(bottomTable);
    }

    public Pixmap getRcvdPixmap() {
        return rcvdPixmap;
    }

    public void setRcvdPixmap(Pixmap rcvdPixmap) {
        this.rcvdPixmap = rcvdPixmap;
    }
}
