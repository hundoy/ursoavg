/**  
* @Title: LayerCtrl.java
* @Description: to control layers 
* @author Hundoy - Zohar  
* @date 2016年3月31日 下午5:43:06
* @version V1.0  
*/
package com.urso.avg.ctrl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.utils.Array;
import com.urso.avg.UrsoAvgGame;
import com.urso.avg.bean.DicBean;
import com.urso.avg.graphics.PicLayer;
import com.urso.avg.graphics.UrsoLayer;

import java.util.HashMap;

/**
 * @author zohar
 *
 */
public class LayerCtrl {
	
	private UrsoAvgGame game;
	
	// store layers in right order
	private Array<UrsoLayer> foreLayerArr;
	private Array<UrsoLayer> backLayerArr;
	
	// store layers via uname
	private HashMap<String, UrsoLayer> foreLayerMap;
	private HashMap<String, UrsoLayer> backLayerMap;
	
	private FrameBuffer foreFb;
	private FrameBuffer backFb;
	
	public static final int LAYER_BACK = 0;
	public static final int LAYER_FORE = 1;
	public static final String  PIC_NAME_PREFIX = "pic";
	
	public LayerCtrl(UrsoAvgGame game){
		this.game = game;
		
		foreLayerArr = new Array<UrsoLayer>();
		backLayerArr = new Array<UrsoLayer>();
		foreLayerMap = new HashMap<String, UrsoLayer>();
		backLayerMap = new HashMap<String, UrsoLayer>();
		foreFb = new FrameBuffer(Format.RGBA8888, UrsoAvgGame.SCW, UrsoAvgGame.SCH, false);
		backFb = new FrameBuffer(Format.RGBA8888, UrsoAvgGame.SCW, UrsoAvgGame.SCH, false);

	}
	
	// add a pic layer and sort layer array to keep the right priority
	public PicLayer addPicLayer(int foreback, String uname){
		freeLayer(foreback, uname); // free firstly
		PicLayer pic = new PicLayer(game, 0, uname);
		if (foreback == LAYER_BACK){
			backLayerMap.put(uname, pic);
			backLayerArr.add(pic);
			backLayerArr.sort();
		} else {
			foreLayerMap.put(uname, pic);
			foreLayerArr.add(pic);
			foreLayerArr.sort();
		}
		
		return pic;
	}

	public void freeLayer(int foreback, String uname){
		if (foreback == LAYER_BACK){
			if (backLayerMap.containsKey(uname)){
				UrsoLayer lay = backLayerMap.get(uname);
				backLayerMap.remove(uname);
				backLayerArr.removeValue(lay, true);
				lay.dispose();
			}
		} else{
			if (foreLayerMap.containsKey(uname)){
				UrsoLayer lay = foreLayerMap.get(uname);
				foreLayerMap.remove(uname);
				foreLayerArr.removeValue(lay, true);
				lay.dispose();
			}
		}
	}
	
	/* 
	 * set properties with a dictionary ( make it easy to extend )
	 * [id=uname, n=picture_name, x=x_position, y=y_position,
	 * vis=is_visible, opa=255(opacity), prior=2400(priority)]
	 */
	public PicLayer setPicLayer(DicBean dic){
		String uname = dic.get("id");
		PicLayer layer = null;
		if (dic.get("page").equals("back")){
			layer = (PicLayer) backLayerMap.get(uname);
		} else if (foreLayerMap.containsKey(uname)){
			layer = (PicLayer) foreLayerMap.get(uname);
		}
		
		// set properties
		String storage = dic.get("n");
		layer.loadPic(storage);
		if (dic.have("x") && dic.have("y")) layer.setPos(dic.getInt("x"), dic.getInt("y"));
		if (dic.have("vis")) layer.setVisible(dic.getBool("vis"));
		if (dic.have("opa")) layer.setOpacity(dic.getFloat("opa"));
		if (dic.have("prior")) layer.setPriority(dic.getInt("prior"));


		if (dic.get("page").equals("back")){
			backLayerMap.put(uname, layer);
		} else{
			foreLayerMap.put(uname, layer);
		}

		return layer;
	}

	public void beforePaint() {
		for (UrsoLayer layer : foreLayerArr){
			if (layer.isVisible()){
				layer.beforePaint();
			}
		}
	}

	public void paint() {
		// paint layers on frame buffer
		foreFb.bind();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		game.batch.begin();
		// paint visible fore layers to fore frame buffer
		for (UrsoLayer layer : foreLayerArr){
			if (layer.isPainting()){
				layer.paint();
			}
		}
		game.batch.end();
		FrameBuffer.unbind();
		
		// paint frame buffer
		game.batch.begin();
		game.batch.setColor(1f, 1f, 1f, 1f);
		game.batch.draw(foreFb.getColorBufferTexture(), 0, 0);
		game.batch.end();
	}

	public void afterPaint() {
		for (UrsoLayer layer : foreLayerArr){
			if (layer.isPainting()){
				layer.afterPaint();
			}
		}		
	}
	
	public void dispose(){
		foreFb.dispose();
		backFb.dispose();
		
		for (UrsoLayer layer : foreLayerArr){
			layer.dispose();
		}
		for (UrsoLayer layer : backLayerArr){
			layer.dispose();
		}
	}
}

