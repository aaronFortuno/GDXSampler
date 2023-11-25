package com.mygdx.game;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.rotateBy;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.common.CustomActor;
import com.mygdx.game.common.SampleBase;
import com.mygdx.game.common.SampleInfo;
import com.mygdx.game.utils.GdxUtils;

public class ActionsSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(ActionsSample.class);

    public static final float WORLD_WIDTH = 1080f;
    public static final float WORLD_HEIGHT = 720f;

    private Viewport viewport;

    private Stage stage;
    private Texture texture;
    private CustomActor customActor;

    @Override
    public void create() {
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        stage = new Stage(viewport);

        texture = new Texture(Gdx.files.internal("raw/custom-actor.png"));
        customActor = new CustomActor(new TextureRegion(texture));
        customActor.setSize(160, 80);
        customActor.setPosition(
                (WORLD_WIDTH - customActor.getWidth()) / 2,
                (WORLD_HEIGHT - customActor.getHeight()) / 2
                );

        stage.addActor(customActor);
        Gdx.input.setInputProcessor(this);

        String LS = System.getProperty("line.separator");
        String TAB = "\t";

        System.out.println(LS + "Press keys." + LS +
                TAB + "1 - RotateBy Action" + LS +
                TAB + "2 - FadeOut Action" + LS +
                TAB + "3 - FadeIn Action" + LS +
                TAB + "4 - ScaleTo Action" + LS +
                TAB + "5 - MoveTo Action" + LS +
                TAB + "6 - Sequential Action" + LS +
                TAB + "7 - Parallel Action"
        );
    }

    @Override
    public boolean keyDown(int keycode) {

        customActor.clearActions();

        if (keycode == Input.Keys.NUM_1) {
            customActor.addAction(rotateBy(90, 1f));
            System.out.println("RotateBy Action");
        }
        if (keycode == Input.Keys.NUM_2) {
            customActor.addAction(fadeOut(1f));
            System.out.println("FadeOut Action");
        }
        if (keycode == Input.Keys.NUM_3) {
            customActor.addAction(fadeIn(1f));
            System.out.println("FadeIn Action");
        }
        if (keycode == Input.Keys.NUM_4) {
            customActor.addAction(scaleTo(2, 2, 1f));
            System.out.println("ScaleTo Action");
        }
        if (keycode == Input.Keys.NUM_5) {
            customActor.addAction(moveTo(150, 40, 1f));
            System.out.println("MoveTo Action");
        }
        if (keycode == Input.Keys.NUM_6) {
            Action action = sequence(
                    fadeOut(1f),
                    fadeIn(1.5f),
                    rotateBy(360, 2f)
            );
            customActor.addAction(action);
            System.out.println("Sequential Action");
        }
        if (keycode == Input.Keys.NUM_7) {
            Action action = parallel(
                    rotateBy(180, 1f),
                    moveBy(50, 50, 2f)
            );
            customActor.addAction(action);
            System.out.println("Parallel Action");
        }

        return true;
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        // no need to call setProjectionMatrix, begin or end, everything is handled internally
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        texture.dispose();
    }
}
