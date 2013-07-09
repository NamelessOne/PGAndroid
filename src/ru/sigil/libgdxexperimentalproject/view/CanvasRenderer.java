package ru.sigil.libgdxexperimentalproject.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ListIterator;

import ru.sigil.libgdxexperimentalproject.model.MyCanvas;
import ru.sigil.libgdxexperimentalproject.model.MyRectangle;
import ru.sigil.libgdxexperimentalproject.model.MyRound;
import ru.sigil.libgdxexperimentalproject.userinterface.CanvasHUD;

public class CanvasRenderer extends Renderer {

    private FrameBuffer fb;

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private MyCanvas myCanvas;

    private SpriteBatch batcher = new SpriteBatch();
    private CanvasHUD canvasHUD;

    public CanvasRenderer(MyCanvas myCanvas, CanvasHUD canvasHUD) {
        this.myCanvas = myCanvas;
        this.canvasHUD = canvasHUD;
        this.cam = new OrthographicCamera(getCAMERA_WIDTH(), getCAMERA_HEIGHT());
        // устанавливаем камеру по центру
        SetCamera(getCAMERA_WIDTH() / 2f, getCAMERA_HEIGHT() / 2f);
    }

    // основной метод, здесь мы отрисовываем все объекты мира
    public void render() {
        if (fb == null)
            fb = new FrameBuffer(Format.RGBA8888, getSCREEN_WIDTH(), getSCREEN_HEIGHT(),
                    true);
        fb.begin();
        ListIterator<MyRectangle> iterator = myCanvas.getRectanglesPool()
                .listIterator();
        //noinspection WhileLoopReplaceableByForEach
        while (iterator.hasNext()) {
            DrawRectangle(iterator.next());
        }
        ListIterator<MyRound> iterator2 = myCanvas.getRoundsPool()
                .listIterator();
        //noinspection WhileLoopReplaceableByForEach
        while (iterator2.hasNext()) {
            DrawCircle(iterator2.next());
        }
        myCanvas.getRectanglesPool().clear();
        myCanvas.getRoundsPool().clear();
        //------------------ Рисуем фон интерфейсной части --------------
        shapeRenderer.begin(ShapeType.FilledRectangle);
        shapeRenderer.setColor(0.52734375f, 0.31640625f, 0.11328125f, 1);
        shapeRenderer.identity();
        shapeRenderer.translate(0, 0, 0);
        shapeRenderer.filledRect(0, 0, getSCREEN_WIDTH(),
                getSCREEN_HEIGHT() / 10);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeType.FilledRectangle);
        shapeRenderer.setColor(0.52734375f, 0.31640625f, 0.11328125f, 1);
        shapeRenderer.identity();
        shapeRenderer.translate(0, 0, 0);
        shapeRenderer.filledRect(0, getSCREEN_HEIGHT() - getSCREEN_HEIGHT() / 10, getSCREEN_WIDTH(),
                getSCREEN_HEIGHT() / 10);
        shapeRenderer.end();
        //--------------------------
        fb.end();
        batcher.begin();
        batcher.draw(fb.getColorBufferTexture(), 0, 0);
        batcher.end();
        canvasHUD.getStage().act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        canvasHUD.getStage().draw();
    }

    private void DrawCircle(MyRound round) {
        shapeRenderer.begin(ShapeType.FilledCircle);
        shapeRenderer.setColor(round.getColor());
        shapeRenderer.identity();
        shapeRenderer.translate(round.getX(), round.getY(), 0);
        shapeRenderer.filledCircle(0, 0, round.getDiameter() / 2);
        shapeRenderer.end();

    }

    private void DrawRectangle(MyRectangle rectangle) {
        shapeRenderer.begin(ShapeType.FilledRectangle);
        shapeRenderer.setColor(rectangle.getColor());
        shapeRenderer.identity();
        shapeRenderer.translate(rectangle.getX2(), rectangle.getY2(), 0);
        shapeRenderer.rotate(0, 0, 1, rectangle.getRotation());
        shapeRenderer.filledRect(0, 0, rectangle.getWidth(),
                rectangle.getDiameter());
        shapeRenderer.end();
    }

    private void sendPicture() {
        //TODO хватаем изображение и отсылаем на сервер
        byte[] b = ScreenUtils.getFrameBufferPixels(false);
    }
}