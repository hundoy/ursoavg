package com.urso.avg.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.urso.avg.UrsoAvgGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		DisplayMode displayMode = LwjglApplicationConfiguration.getDesktopDisplayMode();
		
		config.title = "urso avg";
		config.width = 1069;
		config.height = 600;
		config.vSyncEnabled = true;
		
//		config.setFromDisplayMode(displayMode); // full screen
		
		new LwjglApplication(new UrsoAvgGame(), config);
	}
}
