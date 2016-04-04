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
import com.urso.avg.bean.TexBean;

import static com.urso.avg.tool.ToolUtil.*;

public class AssetCtrl {
	private UrsoAvgGame game;
	
	private HashMap<String, TexBean> textureMap;
	private HashMap<String, TexBean> atlasMap;
	private HashMap<String, String> actualNameMap;
	private String picPath; // this must be end with "/"
	
	public AssetCtrl(UrsoAvgGame game, String picPath){
		this.game = game;
		if (!picPath.endsWith("/")) picPath += "/";
		this.picPath = picPath;
		textureMap = new HashMap<String, TexBean>();
		atlasMap = new HashMap<String, TexBean>();
		
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
				// there is no value in map
				// or the new file is an atlas
				// then add/change the actual file name
				if (!actualNameMap.containsKey(lowname) || actualname.toLowerCase().endsWith(".atlas")){
					actualNameMap.put(lowname, actualname);
				}
			}
		}
	}

	// load a texture by name without extension
	public TexBean loadTexture(String name){
		if (!isnnb(name)) {
			return null;
		}
		
		String lowname = name.toLowerCase();
		if (textureMap.containsKey(lowname)){
			return textureMap.get(name);
		} else if (actualNameMap.containsKey(lowname)){
			String actualName = actualNameMap.get(lowname);
			Texture tex = new Texture(Gdx.files.local(picPath + actualName));
			TexBean bean = new TexBean();
			bean.initTexture(lowname, actualName, picPath + actualName, 
					TexBean.TYPE_TEXTURE, tex);
			textureMap.put(lowname, bean);
			return bean;
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
			TexBean tex = textureMap.get(lowname);
			textureMap.remove(lowname);
			tex.dispose();
		}
	}
	
	// load atlas without extension
	public TexBean loadAtlas(String name){
		if (!isnnb(name)) return null;
		
		String lowname = name.toLowerCase();
		if (atlasMap.containsKey(lowname)){
			return atlasMap.get(name);
		} else if (actualNameMap.containsKey(lowname)){
			String actualName = actualNameMap.get(lowname);
			TextureAtlas atlas = new TextureAtlas(picPath + actualName);
			TexBean bean = new TexBean();
			bean.initAtlas(lowname, actualName, picPath + actualName, 
					TexBean.TYPE_ATLAS, atlas);
			atlasMap.put(lowname, bean);
			return bean;
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
			TexBean atlas = atlasMap.get(lowname);
			atlasMap.remove(lowname);
			atlas.dispose();
		}
	}

	public void dispose() {
		for (TexBean t : textureMap.values()){
			t.dispose();
		}
		textureMap.clear();
	}
}
