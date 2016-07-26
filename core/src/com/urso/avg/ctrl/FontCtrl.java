/** 
* @Title: FontCtrl.java
* @Description: The class is used to control the font used in game.
* @author Hundoy - Zohar  
* @date 2016年3月26日 下午7:39:52
* @version V1.0  
*/
package com.urso.avg.ctrl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.urso.avg.UrsoAvgGame;

public class FontCtrl {
	private UrsoAvgGame game;
	
	public String configPath;
	private BitmapFont font;
	private int initSize;
	
	public FontCtrl(UrsoAvgGame game, String path){
		this.game = game;
		configPath = path;
	}
	
	public BitmapFont loadFont(String name, int size){
		initSize = size;
		FreeTypeFontGenerator fontGen = new FreeTypeFontGenerator(Gdx.files.local(configPath+name));
        FreeTypeFontParameter fontPara = new FreeTypeFontParameter();
        fontPara.size = size;
        fontPara.characters = loadCharacters();
        font = fontGen.generateFont(fontPara);
        fontGen.dispose();
        return font;
	}

	// return the necessary characters
	private String loadCharacters() {
		String charTxt = Gdx.files.local(configPath+"characters.txt").readString();
		return charTxt;
	}

	public void dispose() {
		font.dispose();
	}

	public void draw(String txt, int tx, int ty) {
		font.draw(game.batch, txt, tx+0f, ty+0f);
	}

	public void color(Color col){
		font.setColor(col);
	}

	public void scale(float sx, float sy){
		font.getData().setScale(sx, sy);
	}

	public int getInitSize(){
		return initSize;
	}
}
