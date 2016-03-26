/** 
* @Title: FontCtrl.java
* @Description: The class is used to control the font used in game.
* @author Hundoy - Zohar  
* @date 2016��3��26�� ����7:39:52
* @version V1.0  
*/
package com.urso.avg.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontCtrl {
	
	public String configPath;
	private BitmapFont font;
	
	public FontCtrl(){
		configPath = "data/config/";
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
