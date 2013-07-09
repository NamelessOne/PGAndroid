package ru.sigil.libgdxexperimentalproject.controller;

import android.os.Debug;
import android.util.Log;

import com.badlogic.gdx.math.Vector2;

import ru.sigil.libgdxexperimentalproject.model.MyCanvas;
import ru.sigil.libgdxexperimentalproject.model.MyRectangle;
import ru.sigil.libgdxexperimentalproject.model.MyRound;
import ru.sigil.libgdxexperimentalproject.model.PaintBrush;
import ru.sigil.libgdxexperimentalproject.userinterface.CanvasHUD;

public class CanvasController {

    private MyCanvas canvas;

    private Vector2 circlePos = new Vector2();
    private Vector2 oldCirclePos = new Vector2();
    private boolean isOldLine;
    private CanvasHUD canvasYHUD;

    public CanvasController(MyCanvas canvas, CanvasHUD canvasHUD) {
        this.canvasYHUD = canvasHUD;
        this.canvas = canvas;
        PaintBrush.setColors();
    }

    public void canvasTouchDragged(int x, int y, int pointer) {
        if (!canvasYHUD.getStage().touchDragged(x, y, pointer)) {
            circlePos.x = x;
            circlePos.y = y;
            MyRound mr = new MyRound(PaintBrush.getCircleDiameter(),
                    circlePos.x, circlePos.y, PaintBrush.getCurrentColor());
            canvas.getRoundsPool().add(mr);
            if ((Math.sqrt((oldCirclePos.x - circlePos.x)
                    * (oldCirclePos.x - circlePos.x)
                    + (oldCirclePos.y - circlePos.y)
                    * (oldCirclePos.y - circlePos.y)) > PaintBrush
                    .getCircleDiameter()) && isOldLine) {
                float oldX = oldCirclePos.x;
                float oldY = oldCirclePos.y;
                MyRectangle cr = new MyRectangle(
                        PaintBrush.getCircleDiameter(), oldX, oldY,
                        circlePos.x, circlePos.y, PaintBrush.getCurrentColor());
                canvas.getRectanglesPool().add(cr);
            }
            oldCirclePos.x = circlePos.x;
            oldCirclePos.y = circlePos.y;
            isOldLine = true;
        }
    }

    public void canvasTouchDown(int x, int y, int pointer, int button) {
        if (!canvasYHUD.getStage().touchDown(x, y, pointer, button)) {
            circlePos.x = x;
            circlePos.y = y;
        }
    }

    public void canvasTouchUp(int x, int y, int pointer, int button) {
        if (!canvasYHUD.getStage().touchUp(x, y, pointer, button)) {
            Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            String memMessage = String.format(
                    "Memory: Pss=%.2f MB, Private=%.2f MB, Shared=%.2f MB",
                    memoryInfo.getTotalPss() / 1024.0,
                    memoryInfo.getTotalPrivateDirty() / 1024.0,
                    memoryInfo.getTotalSharedDirty() / 1024.0);
            Log.v("Memory usage", memMessage);
            isOldLine = false;
        }
    }
}
