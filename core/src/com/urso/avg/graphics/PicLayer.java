/** 
* @Title: PicLayer.java
* @Description: graphics layer
* @author Hundoy - Zohar  
* @date 2016年3月30日 下午4:20:11
* @version V1.0
*/
package com.urso.avg.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.urso.avg.UrsoAvgGame;
import com.urso.avg.tool.ToolUtil;

public class PicLayer extends UrsoLayer {
	private String picName;
	private Sprite sp;
	
	public PicLayer(UrsoAvgGame game, int uid, String uname) {
		super(game, uid, uname);
		
		priority = uid;
	}

	// load texture directly
	public void loadPic(String texName){
		if (ToolUtil.isnnb(texName)){
			this.picName = texName;
			Texture tex = game.asset.loadTexture(picName);
			sp = new Sprite(tex);
		}
	}
	
	// load via atlas
	public void loadAtlasPic(String picName){
		if (ToolUtil.isnnb(picName)){
			int index = picName.indexOf(".");
			if (index<=-1 || index==picName.length()-1){
				game.error.formatError("Atlas pic format should be 'ATLAS_NAME.PIC_NAME' !");
			} else{
				String aname = picName.substring(0, index);
				String pname = picName.substring(index+1);
				
			}
		}
	}
}
