package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.common.CustomActor;
import com.mygdx.game.common.SampleBase;
import com.mygdx.game.common.SampleInfo;
import com.mygdx.game.utils.GdxUtils;

public class TableSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(TableSample.class);

    public static final float WORLD_WIDTH = 1080f;
    public static final float WORLD_HEIGHT = 720f;

    private Viewport viewport;

    private Stage stage;
    private Texture texture;

    @Override
    public void create() {
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        stage = new Stage(viewport);

        texture = new Texture(Gdx.files.internal("raw/custom-actor.png"));
        initUi();
    }

    private void initUi() {
        Table table = new Table();
        table.defaults().space(5f);

        for (int i = 0; i < 6; i++) {
            CustomActor customActor = new CustomActor(new TextureRegion(texture));
            // set size for the actor, by default is 0, 0
            customActor.setSize(180, 60);
            table.add(customActor);
            table.row();
        }

        table.row();
        CustomActor actor = new CustomActor(new TextureRegion(texture));
        actor.setSize(200, 40);
        table.add(actor).fillX().expandX().right();
        table.row();


        table.center();
        // center table into the screen
        table.setFillParent(true);
        table.pack();
        stage.addActor(table);
        stage.setDebugAll(true); // draw debug lines
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
