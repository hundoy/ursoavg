/**  
* @Title: AssetCtrl.java
* @Description: asset control class. it is used to store the using texture and atlas
* @author Hundoy - Zohar  
* @date 2016年3月30日 下午4:25:46
* @version V1.0  
*/
package com.urso.avg.ctrl;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.urso.avg.UrsoAvgGame;

import static com.urso.avg.tool.ToolUtil.*;

public class AssetCtrl {
	private UrsoAvgGame game;
	
	private HashMap<String, Texture> textureMap;
	private HashMap<String, TextureAtlas> atlasMap;
	private HashMap<String, String> actualNameMap;
	private String picPath; // this must be end with "/"
	
	public AssetCtrl(UrsoAvgGame game, String picPath){
		this.game = game;
		if (!picPath.endsWith("/")) picPath += "/";
		this.picPath = picPath;
		textureMap = new HashMap<String, Texture>();
		atlasMap = new HashMap<String, TextureAtlas>();
		
		scanPics();
	}
	
	/**
	 * scan the picture path for all the pics' names;
	 */
	private void scanPics() {
		actualNameMap = new  HashMap<String, String>();
		FileHandle[] pics = Gdx.files.local(picPath).list();
		if (isnne(pics)){
			for (FileHandle fh : pics){
				String actualname = fh.name();
				String lowname = actualname.toLowerCase();
				if (actualname.indexOf(".")>-1){
					lowname = actualname.substring(0, actualname.indexOf("."));
				}
				actualNameMap.put(lowname, actualname);
			}
		}
	}
	
	
	
	

	// load a texture by name without extension
	public Texture loadTexture(String name){
		if (!isnnb(name)) {
			return null;
		}
		
		String lowname = name.toLowerCase();
		if (textureMap.containsKey(lowname)){
			return textureMap.get(name);
		} else if (actualNameMap.containsKey(lowname)){
			String actualName = actualNameMap.get(lowname);
			Texture tex = new Texture(Gdx.files.local(picPath + actualName));
			textureMap.put(lowname, tex);
			return tex;
		} else{
			game.error.warnFileNotFound(lowname);
			return null;
		}
	}
	
	// dispose a texture manually
	public void killTexture(String name){
		if (!isnnb(name)) return;
		
		String lowname = name.toLowerCase();
		if (textureMap.containsKey(lowname)){
			Texture tex = textureMap.get(lowname);
			textureMap.remove(lowname);
			tex.dispose();
		}
	}
	
	// load atlas without extension
	public TextureAtlas loadAtlas(String name){
		if (!isnnb(name)) return null;
		
		String lowname = name.toLowerCase();
		if (atlasMap.containsKey(lowname)){
			return atlasMap.get(name);
		} else if (actualNameMap.containsKey(lowname)){
			String actualName = actualNameMap.get(lowname);
			TextureAtlas atlas = new TextureAtlas(picPath + actualName);
			atlasMap.put(lowname, atlas);
			return atlas;
		} else{
			game.error.warnFileNotFound(lowname);
			return null;
		}
	}
	
	// dispose a texture atlas manually
	public void killAtlas(String name){
		if (!isnnb(name)) return;
		
		String lowname = name.toLowerCase();
		if (atlasMap.containsKey(lowname)){
			TextureAtlas atlas = atlasMap.get(lowname);
			atlasMap.remove(lowname);
			atlas.dispose();
		}
	}

	public void dispose() {
		for (Texture t : textureMap.values()){
			t.dispose();
		}
		textureMap.clear();
	}
}
