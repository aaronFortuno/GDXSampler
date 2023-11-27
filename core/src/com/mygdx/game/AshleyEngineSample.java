package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.common.SampleBase;
import com.mygdx.game.common.SampleInfo;
import com.mygdx.game.utils.GdxUtils;

public class AshleyEngineSample extends SampleBase {

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(AshleyEngineSample.class);

    public static final float SPAWN_TIME = 1f;
    public static final float REMOVE_TIME = 3f;

    private Engine engine;

    private Array<Entity> bullets = new Array<Entity>();

    private float spawnTimer;
    private float removeTimer;

    @Override
    public void create() {
        engine = new Engine();
        engine.addEntityListener(new EntityListener() {
            @Override
            public void entityAdded(Entity entity) {
                System.out.println("entityAdded: " + entity);
                System.out.println("total entities: " + engine.getEntities().size());
            }

            @Override
            public void entityRemoved(Entity entity) {
                System.out.println("entityRemoved: " + entity);
                System.out.println("total entities: " + engine.getEntities().size());
            }
        });
        addBullet();
    }

    private void addBullet() {
        Entity bullet = new Entity();
        bullets.add(bullet);
        engine.addEntity(bullet);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        float delta = Gdx.graphics.getDeltaTime();
        engine.update(delta);

        spawnTimer += delta;

        if (spawnTimer > SPAWN_TIME) {
            spawnTimer = 0;
            addBullet();
        }

        removeTimer += delta;

        if (removeTimer > REMOVE_TIME) {
            removeTimer = 0;

            if (bullets.size > 0) {
                Entity bullet = bullets.first();
                bullets.removeValue(bullet, true);
                engine.removeEntity(bullet);
            }
        }
    }
}
