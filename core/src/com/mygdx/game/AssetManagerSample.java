package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.common.SampleBase;
import com.mygdx.game.common.SampleInfo;
import com.mygdx.game.utils.GdxUtils;

public class AssetManagerSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(AssetManagerSample.class);

    private static final String BACKGROUND_BLUE = "raw/background-blue.png";
    private static final String GREEN_CIRCLE = "raw/circle-green.png";
    private static final String RED_CIRCLE = "raw/circle-red.png";
    private static final String FONT = "fonts/ui_decorated_font_32.fnt";

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private AssetManager assetManager;

    private Texture backgroundBlue;
    private Texture greenCircle;
    private Texture redCircle;
    private BitmapFont font;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(1080, 720, camera);
        batch = new SpriteBatch();

        assetManager = new AssetManager();

        // load assets
        assetManager.load(BACKGROUND_BLUE, Texture.class);
        assetManager.load(GREEN_CIRCLE, Texture.class);
        assetManager.load(RED_CIRCLE, Texture.class);
        assetManager.load(FONT, BitmapFont.class);

        // blocks until all resources are loaded into memory
        assetManager.finishLoading();

        // get assets
        backgroundBlue = assetManager.get(BACKGROUND_BLUE);
        greenCircle = assetManager.get(GREEN_CIRCLE);
        redCircle = assetManager.get(RED_CIRCLE);
        font = assetManager.get(FONT);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(backgroundBlue, 0, 0);
        batch.draw(greenCircle, 50, 50);
        batch.draw(redCircle, 50, 200);
        font.draw(batch, "AssetManagerSample", 50, 350);

        batch.end();
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        assetManager.dispose();
    }
}
