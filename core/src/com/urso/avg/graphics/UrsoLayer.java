/** 
* @Title: UrsoLayer.java
* @Description: basic layer class for urso avg
* @author Hundoy - Zohar  
* @date 2016年3月30日 下午4:12:12
* @version V1.0
*/
package com.urso.avg.graphics;

import java.util.HashMap;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.urso.avg.UrsoAvgGame;

public class UrsoLayer {
	protected UrsoAvgGame game;
	
	protected int uid;
	protected String uname;
	protected boolean isVisible;
	protected float opacity;
	protected Vector3 pos;
	protected int priority;
	
	private HashMap<String, UrsoLayer> children;
	
	public UrsoLayer(UrsoAvgGame game, int uid){
		this(game, uid, String.valueOf(uid));
	}

	public UrsoLayer(UrsoAvgGame game, int uid, String uname){
		this.game = game;
		this.uname = uname;
		this.uid = uid;
	}
	
	public void addChild(UrsoLayer layer){
		if (children==null){
			children = new HashMap<String, UrsoLayer>();
		}
		children.put(layer.uname, layer);
	}
	
	// getters and setters
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public float getOpacity() {
		return opacity;
	}

	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}

	public Vector3 getPos() {
		return pos;
	}
	
	public float getX() {
		return pos.x;
	}
	
	public float getY() {
		return pos.y;
	}

	public void setPos(float x, float y) {
		pos.set(x, y, 0);
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}
