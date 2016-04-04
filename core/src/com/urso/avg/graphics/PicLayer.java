/** 
* @Title: PicLayer.java
* @Description: graphics layer
* @author Hundoy - Zohar  
* @date 2016年3月30日 下午4:20:11
* @version V1.0
*/
package com.urso.avg.graphics;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.urso.avg.UrsoAvgGame;
import com.urso.avg.bean.TexBean;
import com.urso.avg.tool.ToolUtil;

public class PicLayer extends UrsoLayer {
	private String picName;
	private Sprite sp;
	private TexBean tex;
	
	public PicLayer(UrsoAvgGame game, int uid, String uname) {
		super(game, uid, uname);
		
		priority = uid*1000;
	}

	// load texture directly
	public void loadPic(String texName){
		if (ToolUtil.isnnb(texName)){
			TexBean bean = game.asset.loadTexture(texName);
			if (bean!=null){
				tex = bean;
				sp = tex.createSprite();
				this.picName = texName;
			}
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
				TexBean bean = game.asset.loadAtlas(aname);
				if (bean!=null){
					Sprite sprite = bean.createSprite(pname);
					if (sprite!=null){
						tex = bean;
						sp = sprite;
						this.picName = picName;
					}
				}
			}
		}
	}
	
	public void disposeSp(){
		if (tex!=null && sp!=null){
			tex.removeSprite(sp);
		}
	}

	
	@Override
	public void beforePaint() {
		super.beforePaint();
		
		if (sp!=null){
			Vector3 vec = getActualPos();
			sp.setPosition(vec.x, vec.y);
			sp.flip(false, true);
		}
	}

	@Override
	public void afterPaint() {
		super.afterPaint();
		
		if (sp!=null){
			sp.flip(false, true);
		}
	}

	@Override
	public void paint() {
		super.paint();
		
		if (sp!=null){
			sp.draw(game.batch);
		}
	}

	// getter and setter
	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public Sprite getSp() {
		return sp;
	}

	public void setSp(Sprite sp) {
		this.sp = sp;
	}
	
	
}
