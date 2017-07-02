package com.urso.avg.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.urso.avg.UrsoAvgGame;
import com.urso.avg.test.CardGame;
import com.urso.avg.test.CubeTets;
import com.urso.avg.test.ShaderSample;
import com.urso.avg.test.SpriteSample;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		DisplayMode displayMode = LwjglApplicationConfiguration.getDesktopDisplayMode();
		
		config.title = "urso avg";
		config.width = 1280;
		config.height = 720;
		config.vSyncEnabled = true;
		
//		config.setFromDisplayMode(displayMode); // full screen
		
//		new LwjglApplication(new ShaderSample(), config);
//		new LwjglApplication(new SpriteSample(), config);
		new LwjglApplication(new UrsoAvgGame(), config);
//		new LwjglApplication(new CubeTets(), config);
	}
}
