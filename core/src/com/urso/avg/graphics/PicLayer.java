/** 
* @Title: PicLayer.java
* @Description: graphics layer
* @author Hundoy - Zohar  
* @date 2016年3月30日 下午4:20:11
* @version V1.0
*/
package com.urso.avg.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.urso.avg.UrsoAvgGame;
import com.urso.avg.bean.PicBean;

public class PicLayer extends UrsoLayer {
	private String picName;
	private PicBean pic;

	public PicLayer(UrsoAvgGame game, int uid, String uname) {
		super(game, uid, uname);
		
		priority = uid*1000;
	}

	public void loadPic(String name){
		pic = game.asset.loadPic(name);
	}
	
	public void dispose(){
		pic.release();
		pic = null;
	}

	@Override
	public void beforePaint() {
		super.beforePaint();
		
		if (pic!=null){
			Vector3 vec = getActualPos();
			Gdx.app.debug("pic pos", vec.toString());
			pic.getSp().setPosition(vec.x, vec.y);
			pic.getSp().setAlpha(opacity);
//			pic.getSp().flip(false, true);
		}
	}

	@Override
	public void afterPaint() {
		super.afterPaint();
		
		if (pic!=null){
//			pic.getSp().flip(false, true);
		}
	}

	@Override
	public void paint() {
		super.paint();
		
		if (pic!=null){
			pic.getSp().draw(game.batch);
		}
	}

	// getter and setter
	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}
}
