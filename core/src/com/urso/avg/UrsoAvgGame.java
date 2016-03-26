/** 
* @Title: UrsoAvgGame.java
* @Description: Entrace of game
* @author Hundoy - Zohar  
* @date 2016年3月26日 下午7:39:52
* @version V1.0  
*/
package com.urso.avg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.urso.avg.tool.FontCtrl;

public class UrsoAvgGame extends Game {
	SpriteBatch batch;
	public FontCtrl fontCtrl;
	
	public static final int SCW = 1069;
	public static final int SCH = 600;	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		fontCtrl = new FontCtrl();
		fontCtrl.loadFont("bgqc.TTF", 18);
		
		this.setScreen(new AvgScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose(){
		batch.dispose();
		fontCtrl.dispose();
	}
}
