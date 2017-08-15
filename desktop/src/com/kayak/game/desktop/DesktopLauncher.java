package com.kayak.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kayakinganimal.coden.CatHop;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = CatHop.WIDTH;
		config.height = CatHop.HEIGHT;

		new LwjglApplication(new CatHop(null), config);
	}
}
