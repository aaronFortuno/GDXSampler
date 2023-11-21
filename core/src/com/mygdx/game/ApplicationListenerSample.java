package com.mygdx.game;

import com.badlogic.gdx.utils.Logger;
import com.mygdx.game.common.SampleBase;
import com.mygdx.game.common.SampleInfo;

public class ApplicationListenerSample extends SampleBase {

	private static final Logger log = new Logger(ApplicationListenerSample.class.getName(), Logger.DEBUG);

	public static final SampleInfo SAMPLE_INFO = new SampleInfo(ApplicationListenerSample.class);
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
