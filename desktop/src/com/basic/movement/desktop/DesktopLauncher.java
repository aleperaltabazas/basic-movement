package com.basic.movement.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.basic.movement.BasicMovementGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 240;
		config.height = 160;
		config.title = "Basic Movement";
		new LwjglApplication(new BasicMovementGame(), config);
	}
}
