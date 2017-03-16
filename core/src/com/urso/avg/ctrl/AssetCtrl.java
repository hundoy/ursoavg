/**  
* @Title: AssetCtrl.java
* @Description: asset control class. it is used to store the using texture and atlas
* @author Hundoy - Zohar  
* @date 2016年3月30日 下午4:25:46
* @version V1.0  
*/
package com.urso.avg.ctrl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.urso.avg.UrsoAvgGame;
import com.urso.avg.bean.PicBean;
import com.urso.avg.bean.PicsrcBean;

import java.util.HashMap;

import static com.zohar.common.util.ToolUtil.isnoe;

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
		picsrcMap = new HashMap<String, PicsrcBean>();

		if (isnoe(paths)){
			for (String path : paths){
				if (!path.endsWith("/")) path += "/";
				FileHandle[] pics = Gdx.files.local(path).list();
				if (isnoe(pics)){
					for (FileHandle fh : pics){
						String actualname = fh.name();
						String lowname = actualname.toLowerCase();
						String exname = "";
						if (lowname.indexOf(".")>-1){
                            exname = lowname.substring(actualname.indexOf("."));
                            lowname = lowname.substring(0, lowname.indexOf("."));
						}
						// Graphics
                        if (exname.equalsIgnoreCase(".atlas")){
                            PicsrcBean ps = new PicsrcBean(lowname, actualname, path+actualname, PicsrcBean.TYPE_ATLAS);
                            picsrcMap.put(lowname, ps);
                        } else if (!picsrcMap.containsKey(lowname) && exname.equalsIgnoreCase(".png") || exname.equalsIgnoreCase(".jpg")){
							PicsrcBean ps = new PicsrcBean(lowname, actualname, path+actualname, PicsrcBean.TYPE_TEXTURE);
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
            picName = fileName.substring(fileName.indexOf(".")+1);
            fileName = fileName.substring(0, fileName.indexOf("."));
		}
		PicBean pic = picsrcMap.get(fileName).createPic(picName);
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
