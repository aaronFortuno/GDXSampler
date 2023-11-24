package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.common.SampleBase;
import com.mygdx.game.common.SampleInfo;

public class BitmapFontSample extends SampleBase {
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(BitmapFontSample.class);

    private static final float WIDTH = 1080f; // world units
    private static final float HEIGHT = 720f; // world units

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private BitmapFont effectFont;
    private BitmapFont uiFont;
    private GlyphLayout glyphLayout = new GlyphLayout();

    @Override
    public void create() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(WIDTH, HEIGHT, camera);
        batch = new SpriteBatch();
        effectFont = new BitmapFont(Gdx.files.internal("fonts/ui_decorated_font_32.fnt"));
        uiFont = new BitmapFont(Gdx.files.internal("fonts/ui_font_32.fnt"));
        uiFont.getData().markupEnabled = true;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        draw();
        batch.end();
    }

    private void draw() {
        String text1 = "Using Bitmap Font";
        effectFont.draw(batch, text1, 50, HEIGHT);
        effectFont.draw(batch, text1, 300, HEIGHT, 100, 0, true);

        String text2 = "Fonts are [RED]cool!";
        glyphLayout.setText(uiFont, text2);
        uiFont.draw(batch, text2, 50, HEIGHT - 50);
        uiFont.draw(batch, glyphLayout, 50, HEIGHT - 100);
    }

    @Override
    public void dispose() {
        batch.dispose();
        effectFont.dispose();
        uiFont.dispose();
    }
}
