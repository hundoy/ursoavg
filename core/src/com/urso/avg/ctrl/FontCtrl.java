/** 
* @Title: FontCtrl.java
* @Description: The class is used to control the font used in game.
* @author Hundoy - Zohar  
* @date 2016年3月26日 下午7:39:52
* @version V1.0  
*/
package com.urso.avg.ctrl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.urso.avg.UrsoAvgGame;

public class FontCtrl {
	private UrsoAvgGame game;
	
	public String configPath;
	private BitmapFont font;
	
	public FontCtrl(UrsoAvgGame game, String path){
		this.game = game;
		configPath = path;
	}
	
	public BitmapFont loadFont(String name, int size){
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
}
