package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.common.SampleBase;
import com.mygdx.game.common.SampleInfo;
import com.mygdx.game.utils.GdxUtils;

public class ShapeRendererSample extends SampleBase {
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(ShapeRendererSample.class);

    private static final float WORLD_HEIGHT = 20f;
    private static final float WORLD_WIDTH = 40f;

    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer renderer;

    private boolean drawGrid = true;
    private boolean drawCircles = true;
    private boolean drawRectangles = true;
    private boolean drawPoints = true;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        renderer = new ShapeRenderer();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height); // not centering camera
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();
        renderer.setProjectionMatrix(camera.combined);

        if (drawGrid) {
            drawGrid();
        }

        if (drawCircles) {
            drawCircles();
        }

        if (drawRectangles) {
            drawRectangles();
        }

        if (drawPoints) {
            drawPoints();
        }
    }

    private void drawGrid() {
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);
        int worldWidth = (int) WORLD_WIDTH;
        int worldHeight = (int) WORLD_HEIGHT;

        // vertical lines
        for (int x = -worldWidth; x < worldHeight; x++) {
            renderer.line(x, -worldHeight, x, worldHeight);
        }

        // horizontal lines
        for (int y = -worldHeight; y < worldHeight; y++) {
            renderer.line(-worldWidth, y, worldWidth, y);
        }

        // drawing world axis
        renderer.setColor(Color.RED);
        renderer.line(0.0f, -worldHeight, 0.0f, worldHeight); // vertical
        renderer.line(-worldWidth, 0.0f, worldWidth, 0.0f); // horizontal

        renderer.end();
    }

    private void drawCircles() {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLUE);

        renderer.circle(0.0f, 0.0f, 2, 30);
        renderer.circle(3, 3, 1, 10);

        renderer.end();
    }

    private void drawRectangles() {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GREEN);

        renderer.rect(-8, 4, 4, 2);
        renderer.rect(-11.3f, 3.5f, 1, 5);

        renderer.end();
    }

    private void drawPoints() {
        // it will look like a dead pixel
        renderer.begin(ShapeRenderer.ShapeType.Point);
        renderer.setColor(Color.ORANGE);

        renderer.point(10.5f, 5.5f, 0);
        renderer.point(10.7f, 5.5f, 0);

        renderer.end();

        // it will look like a square
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        renderer.point(10.5f, -5.5f, 0);

        renderer.end();
    }
    @Override
    public void dispose() {
        renderer.dispose();
    }
}
