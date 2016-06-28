/**  
* @Title: TexBean.java
* @Description: a bean store a texture or atlas
* @author Hundoy - Zohar  
* @date 2016年4月3日 下午10:28:53
* @version V1.0  
*/
package com.urso.avg.bean;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class TexBean {
	private String lowName;
	private String fileName;
	private String filePath;
	private int type; // 0- texture 1-atlas
	private Texture texture;
	private TextureAtlas atlas;
	
	private Array<Sprite> spArr; // store all the sprites using this texture/atlas
	
	public static final int TYPE_TEXTURE = 0;
	public static final int TYPE_ATLAS = 1;
	
	public TexBean(){
		spArr = new Array<Sprite>();
	}
	
	private void init(String lowName, String fileName, String filePath, 
			int type){
		this.lowName = lowName;
		this.fileName = fileName;
		this.filePath = filePath;
		this.type = type;
	}
	
	public void initTexture(String lowName, String fileName, String filePath, Texture texture){
		init(lowName, fileName, filePath, TYPE_TEXTURE);
		this.texture = texture;
	}
	
	public void initAtlas(String lowName, String fileName, String filePath, TextureAtlas atlas){
		init(lowName, fileName, filePath, TYPE_ATLAS);
		this.atlas = atlas;
	}
	
	// create a sprite with texture
	public Sprite createSprite(){
		if (texture != null){
			Sprite sp = new Sprite(texture);
			spArr.add(sp);
			return sp;
		}
		return null;
	}
	
	// create a sprite with atlas name
	public Sprite createSprite(String name){
		if (atlas != null){
			Sprite sp = atlas.createSprite(name);
			spArr.add(sp);
			return sp;
		}
		return null;
	}
	
	public void dispose() {
		if (texture != null){
			texture.dispose();
		}
		
		if (atlas != null){
			atlas.dispose();
		}
	}
	
	public void removeSprite(Sprite sp) {
		if (spArr.contains(sp, true)){
			spArr.removeValue(sp, true);
			sp.getTexture().dispose();
		}
	}
	
	// getter and setter
	public Texture getTexture(){
		return texture;
	}
	public TextureAtlas getAtlas(){
		return atlas;
	}

	public String getLowName() {
		return lowName;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public int getType() {
		return type;
	}
}
