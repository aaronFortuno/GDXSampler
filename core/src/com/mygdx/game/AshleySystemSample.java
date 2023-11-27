package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ashley.component.PositionComponent;
import com.mygdx.game.ashley.component.SizeComponent;
import com.mygdx.game.ashley.component.TextureComponent;
import com.mygdx.game.ashley.system.RenderSystem;
import com.mygdx.game.common.SampleBase;
import com.mygdx.game.common.SampleInfo;
import com.mygdx.game.utils.GdxUtils;

public class AshleySystemSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(AshleySystemSample.class);

    private static final float WORLD_WIDTH = 10.8f; // world units
    private static final float WORLD_HEIGHT = 7.2f; // world units

    private static final String LEVEL_BG = "raw/level-bg.png";
    private static final String CHARACTER = "raw/character.png";

    private static final Family FAMILY = Family.all(TextureComponent.class).get();
    private AssetManager assetManager;
    private Viewport viewport;
    private SpriteBatch batch;
    private Engine engine;

    private Entity entity;
    private TextureComponent texture;

    @Override
    public void create() {
        assetManager = new AssetManager();
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        batch = new SpriteBatch();
        engine = new Engine();

        assetManager.load(LEVEL_BG, Texture.class);
        assetManager.load(CHARACTER, Texture.class);
        assetManager.finishLoading();

        Gdx.input.setInputProcessor(this);

        addBackground();
        addCharacter();

        engine.addEntityListener(FAMILY, new EntityListener() {
            @Override
            public void entityAdded(Entity entity) {
                System.out.println("Family size: " + engine.getEntitiesFor(FAMILY));
                System.out.println("Total size: " + engine.getEntities().size());
                System.out.println("Entity added to family");
            }

            @Override
            public void entityRemoved(Entity entity) {
                System.out.println("Family size: " + engine.getEntitiesFor(FAMILY));
                System.out.println("Total size: " + engine.getEntities().size());
                System.out.println("Entity added to family");
            }
        });

        engine.addSystem(new RenderSystem(viewport, batch));
    }

    private void addBackground() {
        PositionComponent position = new PositionComponent();
        position.x = 0;
        position.y = 0;

        SizeComponent size = new SizeComponent();
        size.width = WORLD_WIDTH;
        size.height = WORLD_HEIGHT;

        TextureComponent texture = new TextureComponent();
        texture.texture = assetManager.get(LEVEL_BG);

        Entity entity = new Entity();
        entity.add(position);
        entity.add(size);
        entity.add(texture);

        engine.addEntity(entity);
    }

    private void addCharacter() {
        PositionComponent position = new PositionComponent();
        position.x = 0;
        position.y = 0;

        SizeComponent size = new SizeComponent();
        size.width = 2;
        size.height = 2;

        texture = new TextureComponent();
        texture.texture = assetManager.get(CHARACTER);

        entity = new Entity();
        entity.add(position);
        entity.add(size);
        entity.add(texture);

        engine.addEntity(entity);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.R) {
            if (FAMILY.matches(entity)) {
                entity.remove(TextureComponent.class);
            }
        } else if (keycode == Input.Keys.A) {
            if (!FAMILY.matches(entity)) {
                entity.add(texture);
            }
        }
        return true;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        float delta = Gdx.graphics.getDeltaTime();
        engine.update(delta);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        batch.dispose();
    }
}
