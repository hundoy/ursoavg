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
import com.urso.avg.UrsoAvgGame;
import com.urso.avg.bean.PicBean;
import com.urso.avg.bean.PicsrcBean;

import static com.urso.avg.tool.ToolUtil.*;

public class AssetCtrl {
	private UrsoAvgGame game;

	private HashMap<String, PicsrcBean> picsrcMap;
	private String[] paths;

	public AssetCtrl(UrsoAvgGame game, String[] paths){
		this.game = game;
		this.paths = paths;
		scanFiles();
	}
	
	/**
	 * scan the picture path for all the pics' names;
	 */
	private void scanFiles() {
		if (isnne(paths)){
			for (String path : paths){
				if (!path.endsWith("/")) path += "/";
				FileHandle[] pics = Gdx.files.local(path).list();
				if (isnne(pics)){
					for (FileHandle fh : pics){
						String actualname = fh.name();
						String lowname = actualname.toLowerCase();
						String exname = "";
						if (lowname.indexOf(".")>-1){
							lowname = lowname.substring(0, lowname.indexOf("."));
							exname = lowname.substring(actualname.indexOf("."));
						}
						// Graphics
						if (exname.equalsIgnoreCase(".png") || exname.equalsIgnoreCase(".jpg")){
							PicsrcBean ps = new PicsrcBean(lowname, actualname, path+actualname, PicsrcBean.TYPE_TEXTURE);
							picsrcMap.put(lowname, ps);
						} else if (exname.equalsIgnoreCase(".atlas")){
							PicsrcBean ps = new PicsrcBean(lowname, actualname, path+actualname, PicsrcBean.TYPE_ATLAS);
							picsrcMap.put(lowname, ps);
						}
						// Sound
						// XXX TO DO
					}
				}
			}
		}
	}

	// load pic via name
	// load texture: use file name directly
	// load atlas: use atlasName.picName
	public PicBean loadPic(String name){
		String fileName = name.toLowerCase();
		String picName = null;
		if (name.contains(".")){
			// atlas
			fileName = fileName.substring(0, fileName.indexOf("."));
			picName = fileName.substring(fileName.indexOf(".")+1);
		}
		PicBean pic = new PicBean(picsrcMap.get(fileName), picName);
		return pic;
	}

	// dispose a pic manually
	public void killPic(String name){
		picsrcMap.get(name.toLowerCase()).release();
	}

	public void dispose() {
		for (PicsrcBean ps: picsrcMap.values()){
			ps.release();
		}
	}
}
