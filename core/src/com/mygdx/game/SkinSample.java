package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.common.SampleBase;
import com.mygdx.game.common.SampleInfo;
import com.mygdx.game.utils.GdxUtils;

public class SkinSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(SkinSample.class);

    private static final String UI_SKIN = "ui/uiskin.json";
    private static final float WORLD_WIDTH = 1080f;
    private static final float WORLD_HEIGHT = 720f;

    private AssetManager assetManager;
    private Viewport viewport;
    private Stage stage;
    private Skin skin;

    @Override
    public void create() {
        assetManager = new AssetManager();

        assetManager.load(UI_SKIN, Skin.class);
        assetManager.finishLoading();

        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        stage = new Stage(viewport);
        skin = assetManager.get(UI_SKIN);

        Gdx.input.setInputProcessor(stage);

        initUi();
    }

    private void initUi() {
        Table table = new Table();
        table.defaults().pad(20);

        for (int i = 0; i < 4; i++) {
            TextButton textButton = new TextButton("Button" + i, skin);
            textButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println("event: " + event + " actor: " + actor);
                }
            });
            table.add(textButton);
        }

        table.row();

        for (int i = 0; i < 2; i++) {
            CheckBox checkBox = new CheckBox("Checkbox " + i, skin, "custom");
            checkBox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println("event: " + event + " actor: " + actor);
                }
            });
            table.add(checkBox);
        }

        table.center();
        table.setFillParent(true);
        table.debugAll();
        table.pack();

        stage.addActor(table);
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
    }
}
