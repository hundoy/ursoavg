/** 
* @Title: UrsoAvgGame.java
* @Description: Entrace of game
* @author Hundoy - Zohar  
* @date 2016��3��26�� ����7:39:52
* @version V1.0  
*/
package com.urso.avg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.urso.avg.ctrl.AssetCtrl;
import com.urso.avg.ctrl.ErrorCtrl;
import com.urso.avg.ctrl.FontCtrl;

public class UrsoAvgGame extends Game {
	SpriteBatch batch;
	public FontCtrl font;
	public AssetCtrl asset;
	public ErrorCtrl error;
	
	public static final int SCW = 1069;
	public static final int SCH = 600;	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		// init font control
		font = new FontCtrl(this, "data/config/");
		font.loadFont("bgqc.TTF", 18);
		
		// init asset control
		asset = new AssetCtrl(this, "data/graphics/");
		
		// init error control
		error = new ErrorCtrl(this, "");
		
		this.setScreen(new AvgScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose(){
		batch.dispose();
		font.dispose();
		asset.dispose();
		error.dispose();
	}
}
