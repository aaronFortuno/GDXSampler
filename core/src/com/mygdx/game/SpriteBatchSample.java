package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.common.SampleBase;
import com.mygdx.game.common.SampleInfo;
import com.mygdx.game.utils.GdxUtils;

public class SpriteBatchSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(SpriteBatchSample.class);

    private static final float WORLD_WIDTH = 10.8f; // world units
    private static final float WORLD_HEIGHT = 7.2f; // world units

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private Texture texture;
    private Color oldColor;

    private int width = 1; // world units
    private int height = 1; // world units

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();

        oldColor = new Color();

        texture = new Texture(Gdx.files.internal("raw/character.png"));
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
        batch.draw(texture,
                1, 1,                               // x, y <<< in world units (100 pixels)
                width / 2f, height / 2f,           // originX, originY
                width, height,                            // width, height
                1.0f, 1.0f,                         // scaleX, scaleY
                0.0f,                                     // rotation
                0, 0,                                // srcX, srcY
                texture.getWidth(), texture.getHeight(),  // srcWidth, srcHeight
                false, false                              // flipX, flipY
                );
        batch.draw(texture,
                4, 2,                               // x, y <<< in world units (100 pixels)
                width / 2f, height / 2f,           // originX, originY
                width, height,                            // width, height
                2.0f, 2.0f,                         // scaleX, scaleY
                0.0f,                                     // rotation
                0, 0,                                // srcX, srcY
                texture.getWidth(), texture.getHeight(),  // srcWidth, srcHeight
                false, false                              // flipX, flipY
        );
        oldColor.set(batch.getColor());
        batch.draw(texture,
                6, 2,                               // x, y <<< in world units (100 pixels)
                width / 2f, height / 2f,           // originX, originY
                width, height,                            // width, height
                2.0f, 2.0f,                         // scaleX, scaleY
                0.0f,                                     // rotation
                0, 0,                                // srcX, srcY
                texture.getWidth(), texture.getHeight(),  // srcWidth, srcHeight
                false, true                              // flipX, flipY
        );
        batch.setColor(Color.GREEN);
        batch.draw(texture,
                8, 1,                               // x, y <<< in world units (100 pixels)
                width / 2f, height / 2f,           // originX, originY
                width, height,                            // width, height
                2.0f, 2.0f,                         // scaleX, scaleY
                90.0f,                                     // rotation
                0, 0,                                // srcX, srcY
                texture.getWidth(), texture.getHeight(),  // srcWidth, srcHeight
                false, false                              // flipX, flipY
        );
        batch.setColor(oldColor);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);

    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
    }
}

