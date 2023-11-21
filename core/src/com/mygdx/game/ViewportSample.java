package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.common.SampleBase;
import com.mygdx.game.common.SampleInfo;
import com.mygdx.game.utils.GdxUtils;

public class ViewportSample extends SampleBase {
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(ViewportSample.class);

    private static final float WORLD_WIDTH = 800f; // world units
    private static final float WORLD_HEIGHT = 600f; // world units

    private OrthographicCamera camera;
    private Viewport currentViewport;
    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;

    private ArrayMap<String, Viewport> viewports = new ArrayMap<>();

    private int currentViewportIndex;
    private String currentViewportName;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("raw/level-bg.png"));
        font = new BitmapFont(Gdx.files.internal("fonts/oswald-32.fnt"));

        createViewports();
        selectNextViewport();

        Gdx.input.setInputProcessor(this);
    }

    private void createViewports() {
        viewports.put(StretchViewport.class.getSimpleName(),
                new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
        viewports.put(FitViewport.class.getSimpleName(),
                new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
        viewports.put(FillViewport.class.getSimpleName(),
                new FillViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
        viewports.put(ScreenViewport.class.getSimpleName(),
                new ScreenViewport(camera));
        viewports.put(ExtendViewport.class.getSimpleName(),
                new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera));
        currentViewportIndex = -1;
    }

    private void selectNextViewport() {
        currentViewportIndex = (currentViewportIndex + 1) % viewports.size;
        currentViewport = viewports.getValueAt(currentViewportIndex);
        currentViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        currentViewportName = viewports.getKeyAt(currentViewportIndex);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        draw();

        batch.end();
    }

    private void draw() {
        batch.draw(texture, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        font.draw(batch, currentViewportName, 50, 100);
    }

    @Override
    public void resize(int width, int height) {
        currentViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        font.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        selectNextViewport();
        return true;
    }
}
