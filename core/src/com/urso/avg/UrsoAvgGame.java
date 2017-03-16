/** 
* @Title: UrsoAvgGame.java
* @Description: Entrace of game
* @author Hundoy - Zohar  
* @date 2016��3��26�� ����7:39:52
* @version V1.0  
*/
package com.urso.avg;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.koko.core.KokoSayer;
import com.urso.avg.ctrl.*;
import com.urso.avg.input.UrsoInpro;

public class UrsoAvgGame extends Game {
	public SpriteBatch batch;
	public LogicCtrl logic;
	public FontCtrl font;
	public AssetCtrl asset;
	public ErrorCtrl error;
	public LayerCtrl layer;
	public KokoSayer sayer;
	
	public static final int SCW = 1069;
	public static final int SCH = 600;	
	
	@Override
	public void create () {
//		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		batch = new SpriteBatch();
		
		// init font control
		font = new FontCtrl(this, "data/config/");
		font.loadFont("siyuan/siyuan.fnt", 20);
		
		// init asset control
		asset = new AssetCtrl(this, new String[]{"data/graphics/"});
		
		// init error control
		error = new ErrorCtrl(this, "");
		
		// init layer control
		layer = new LayerCtrl(this);

//		logic = new LogicCtrl(this);

		// init kokosayer
		sayer = new KokoSayer("data/config/kokoconfig.json");
		sayer.init("data/scenario/", "first");

		Gdx.input.setInputProcessor(new UrsoInpro(this));

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
		layer.dispose();
	}
}
