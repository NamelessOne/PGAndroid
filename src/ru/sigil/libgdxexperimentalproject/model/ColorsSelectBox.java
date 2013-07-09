package ru.sigil.libgdxexperimentalproject.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Pools;

import java.util.ArrayList;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.removeActor;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

public class ColorsSelectBox extends Widget {
    private static final Vector2 tmpCoordinates = new Vector2();

    private SelectBoxStyle style;
    // -------start
    private ArrayList<Color> colors;
    private Pixmap pixmap = new Pixmap(PaintBrush.getCircleDiameter(),
            PaintBrush.getCircleDiameter(), Pixmap.Format.RGBA8888);
    private Texture texture = new Texture(pixmap);
    // -------end
    private int selectedIndex = 0;
    private final Vector2 screenCoordinates = new Vector2();
    private SelectList list;
    private float prefWidth, prefHeight;
    private ClickListener clickListener;

    public ColorsSelectBox(Skin skin, ArrayList<Color> colors) {
        this(skin.get(SelectBoxStyle.class), colors);
    }

    public ColorsSelectBox(Skin skin, String styleName, ArrayList<Color> colors) {
        this(skin.get(styleName, SelectBoxStyle.class), colors);
    }

    public ColorsSelectBox(SelectBoxStyle style, ArrayList<Color> colors) {
        this.colors = colors;
        setStyle(style);
        setWidth(getPrefWidth());
        setHeight(getPrefHeight());
        addListener(clickListener = new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if (pointer == 0 && button != 0)
                    return false;
                if (list != null && list.getParent() != null) {
                    hideList();
                    return true;
                }
                Stage stage = getStage();
                stage.screenToStageCoordinates(tmpCoordinates.set(screenCoordinates));
                list = new SelectList(tmpCoordinates.x, tmpCoordinates.y);
                stage.addActor(list);
                return true;
            }
        });
    }

    void setStyle(SelectBoxStyle style) {
        if (style == null)
            throw new IllegalArgumentException("style cannot be null.");
        this.style = style;
    }

    /**
     * Returns the select box's style. Modifying the returned style may not have
     * an effect until {@link #setStyle(SelectBoxStyle)} is called.
     */
    public SelectBoxStyle getStyle() {
        return style;
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        Drawable background;
        if (list != null && list.getParent() != null
                && style.backgroundOpen != null)
            background = style.backgroundOpen;
        else if (clickListener.isOver() && style.backgroundOver != null)
            background = style.backgroundOver;
        else
            background = style.background;

        Color color = (Color) colors.toArray()[selectedIndex];// getColor();
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();

        batch.setColor(color.r, color.g, color.b, color.a);
        background.draw(batch, x, y, width, height);

        // calculate screen coords where list should be displayed
        getStage().toScreenCoordinates(screenCoordinates.set(x, y),
                batch.getTransformMatrix());
    }

    /**
     * Sets the selected item via it's index
     *
     * @param selection the selection index
     */
    public void setSelection(int selection) {
        this.selectedIndex = selection;
    }

    public void setSelection(Color color) {
        for (int i = 0; i < colors.size(); i++) {
            if (colors.toArray()[i].equals(color)) {
                selectedIndex = i;
            }
        }
    }

    /**
     * @return the index of the current selection. The top item has an index of
     *         0
     */
    public int getSelectionIndex() {
        return selectedIndex;
    }

    /**
     * @return the string of the currently selected item
     */
    public Color getSelection() {
        return (Color) colors.toArray()[selectedIndex];
    }

    public float getPrefWidth() {
        return prefWidth;
    }

    public float getPrefHeight() {
        return prefHeight;
    }

    void hideList() {
        if (list == null || list.getParent() == null)
            return;

        getStage().removeCaptureListener(list.stageListener);
        list.addAction(sequence(fadeOut(0.15f, Interpolation.fade),
                removeActor()));
    }

    class SelectList extends Actor {
        final Vector2 oldScreenCoords = new Vector2();
        float itemHeight;
        float textOffsetX, textOffsetY;
        int listSelectedIndex = ColorsSelectBox.this.selectedIndex;

        InputListener stageListener = new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if (pointer == 0 && button != 0)
                    return false;
                stageToLocalCoordinates(tmpCoordinates.set(event.getStageX(),
                        event.getStageY()));
                x = tmpCoordinates.x;
                y = tmpCoordinates.y;
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    listSelectedIndex = (int) ((getHeight()
                            - style.listBackground.getTopHeight() - y) / itemHeight);
                    listSelectedIndex = Math.max(0, listSelectedIndex);
                    listSelectedIndex = Math.min(colors.size() - 1,
                            listSelectedIndex);
                    selectedIndex = listSelectedIndex;
                    PaintBrush.setCurrentColor((Color) colors.toArray()[selectedIndex]);
                    if (colors.size() > 0) {
                        ChangeEvent changeEvent = Pools
                                .obtain(ChangeEvent.class);
                        ColorsSelectBox.this.fire(changeEvent);
                        Pools.free(changeEvent);
                    }
                }
                return true;
            }

            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                hideList();
            }

            public boolean mouseMoved(InputEvent event, float x, float y) {
                stageToLocalCoordinates(tmpCoordinates.set(event.getStageX(),
                        event.getStageY()));
                x = tmpCoordinates.x;
                y = tmpCoordinates.y;
                if (x > 0 && x < getWidth() && y > 0 && y < getHeight()) {
                    listSelectedIndex = (int) ((getHeight()
                            - style.listBackground.getTopHeight() - y) / itemHeight);
                    listSelectedIndex = Math.max(0, listSelectedIndex);
                    listSelectedIndex = Math.min(colors.size() - 1,
                            listSelectedIndex);
                }
                return true;
            }
        };

        public SelectList(float x, float y) {
            setBounds(x, 0, ColorsSelectBox.this.getWidth(), 100);
            this.oldScreenCoords.set(screenCoordinates);
            layout();
            Stage stage = ColorsSelectBox.this.getStage();
            float height = getHeight();
            if (y - height < 0
                    && y + ColorsSelectBox.this.getHeight() + height < stage
                    .getCamera().viewportHeight)
                setY(y + ColorsSelectBox.this.getHeight());
            else
                setY(y - height);
            stage.addCaptureListener(stageListener);
            getColor().a = 0;
            addAction(fadeIn(0.3f, Interpolation.fade));
        }

        private void layout() {
            final BitmapFont font = style.font;
            final Drawable listSelection = style.listSelection;
            final Drawable listBackground = style.listBackground;

            itemHeight = font.getCapHeight() + -font.getDescent() * 2
                    + style.itemSpacing;
            itemHeight += listSelection.getTopHeight()
                    + listSelection.getBottomHeight();

            textOffsetX = listSelection.getLeftWidth() + style.itemSpacing;
            textOffsetY = listSelection.getTopHeight() + -font.getDescent()
                    + style.itemSpacing / 2;

            setWidth(ColorsSelectBox.this.getWidth());
            setHeight(colors.size() * itemHeight
                    + listBackground.getTopHeight()
                    + listBackground.getBottomHeight());
        }

        @Override
        public void draw(SpriteBatch batch, float parentAlpha) {
            final Drawable listBackground = style.listBackground;
            final Drawable listSelection = style.listSelection;

            float x = getX();
            float y = getY();
            float width = getWidth();
            float height = getHeight();

            Color color = getColor();
            batch.setColor(color.r, color.g, color.b, color.a);
            listBackground.draw(batch, x, y, width, height);

            width -= listBackground.getLeftWidth()
                    + listBackground.getRightWidth();
            x += listBackground.getLeftWidth();
            float posY = height - listBackground.getTopHeight();
            for (int i = 0; i < colors.size(); i++) {
                listSelection.draw(batch, x, y + posY - itemHeight, width,
                        itemHeight);
                pixmap.setColor((Color) colors.toArray()[i]);
                pixmap.fillRectangle(0, 0, (int) itemHeight, (int) width);
                texture.draw(pixmap, 0, 0);
                texture.bind();
                batch.draw(texture, x, y + posY - itemHeight, width, itemHeight);
                posY -= itemHeight;
            }
        }

        @Override
        public Actor hit(float x, float y, boolean touchable) {
            return this;
        }

        public void act(float delta) {
            super.act(delta);
            if (screenCoordinates.x != oldScreenCoords.x
                    || screenCoordinates.y != oldScreenCoords.y)
                hideList();
        }
    }

    static public class SelectBoxStyle {
        public Drawable background;
        /**
         * Optional.
         */
        public Drawable backgroundOver, backgroundOpen;
        public Drawable listBackground;
        public Drawable listSelection;
        public BitmapFont font;
        public Color fontColor = new Color(1, 1, 1, 1);
        public float itemSpacing = 10;

        public SelectBoxStyle() {
        }

        public SelectBoxStyle(BitmapFont font, Color fontColor,
                              Drawable background, Drawable listBackground,
                              Drawable listSelection) {
            this.background = background;
            this.listBackground = listBackground;
            this.listSelection = listSelection;
            this.font = font;
            this.fontColor.set(fontColor);
        }

        public SelectBoxStyle(SelectBoxStyle style) {
            this.background = style.background;
            this.listBackground = style.listBackground;
            this.listSelection = style.listSelection;
            this.font = style.font;
            this.fontColor.set(style.fontColor);
        }
    }
}