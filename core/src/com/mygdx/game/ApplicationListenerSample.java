package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;

public class ApplicationListenerSample implements ApplicationListener {

	private static final Logger log = new Logger(ApplicationListenerSample.class.getName(), Logger.DEBUG);
	private boolean renderInterrupted = true;
	// used to initialize game and load resources
	@Override
	public void create() {
		log.debug("create()");
	}

	// used to handle setting a new screen size
	@Override
	public void resize(int width, int height) {
		log.debug("resize() width = " + width + " height = " + height);
	}

	// used to update and render the game elements, called 60 times per second
	@Override
	public void render() {
		if (renderInterrupted) {
			log.debug("render()");
			renderInterrupted = false;
		}
	}

	// used to save game state when it loses focus, which does not involve
	// the actual gameplay being paused unless the develop wants it to pause
	@Override
	public void pause() {
		log.debug("pause()");
		renderInterrupted = true;
	}

	// used to handle the game coming back from being paused and restores game state
	@Override
	public void resume() {
		log.debug("resume()");
		renderInterrupted = true;

	}

	// used to free resources and cleanup
	@Override
	public void dispose() {
		log.debug("dispose()");
	}
}
