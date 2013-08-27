package ru.sigil.libgdxexperimentalproject.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.io.File;

import ru.sigil.libgdxexperimentalproject.userinterface.ReceivedPictureHUD;

public class ReceivedPictureRenderer extends Renderer{
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Texture rcvdTex;

    private SpriteBatch batcher = new SpriteBatch();
    private ReceivedPictureHUD receivedPictureHUD;

    public ReceivedPictureRenderer(ReceivedPictureHUD receivedPictureHUD) {
        this.receivedPictureHUD = receivedPictureHUD;
        this.cam = new OrthographicCamera(getCAMERA_WIDTH(), getCAMERA_HEIGHT());
        // устанавливаем камеру по центру
        SetCamera(getCAMERA_WIDTH() / 2f, getCAMERA_HEIGHT() / 2f);
    }

    // основной метод, здесь мы отрисовываем все объекты мира
    public void render() {
        if(receivedPictureHUD.getRcvdPixmap()!=null)
        {
            rcvdTex = new Texture(receivedPictureHUD.getRcvdPixmap());
            receivedPictureHUD.getRcvdPixmap().dispose();
            receivedPictureHUD.setRcvdPixmap(null);
            FileHandle image = Gdx.files.external("directory/rcvd.png");
            image.delete();
        }
        //------------------ Рисуем фон интерфейсной части --------------
        shapeRenderer.begin(ShapeRenderer.ShapeType.FilledRectangle);
        shapeRenderer.setColor(0.52734375f, 0.31640625f, 0.11328125f, 1);
        shapeRenderer.identity();
        shapeRenderer.translate(0, 0, 0);
        shapeRenderer.filledRect(0, 0, getSCREEN_WIDTH(),
                getSCREEN_HEIGHT() / 10);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.FilledRectangle);
        shapeRenderer.setColor(0.52734375f, 0.31640625f, 0.11328125f, 1);
        shapeRenderer.identity();
        shapeRenderer.translate(0, 0, 0);
        shapeRenderer.filledRect(0, getSCREEN_HEIGHT() - getSCREEN_HEIGHT() / 10, getSCREEN_WIDTH(),
                getSCREEN_HEIGHT() / 10);
        shapeRenderer.end();
        //--------------------------
        batcher.begin();
        if(rcvdTex!=null)
        {
            //TODO сделать масштабирование
            //batcher.draw(rcvdTex, 0, getSCREEN_HEIGHT() / 10);
            batcher.draw(rcvdTex, 0, getSCREEN_HEIGHT()/10, getSCREEN_WIDTH(), getSCREEN_HEIGHT() - 2 *getSCREEN_HEIGHT()/10, 0, 0, rcvdTex.getWidth(), rcvdTex.getHeight(), false, false);
            //batcher.draw(rcvdTex, 0, getSCREEN_HEIGHT()/10, 100, 100);
        }
        batcher.end();
        receivedPictureHUD.getStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        receivedPictureHUD.getStage().draw();
    }
}
