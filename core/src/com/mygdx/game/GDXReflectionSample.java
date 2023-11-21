package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.common.SampleBase;
import com.mygdx.game.common.SampleInfo;

public class GDXReflectionSample extends SampleBase {
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(GDXReflectionSample.class);


    private Viewport viewport;

    @Override
    public void create() {
        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(1080, 720, camera);
        new SpriteBatch();
        new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {

    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

}
