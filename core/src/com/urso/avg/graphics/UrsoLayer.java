/** 
* @Title: UrsoLayer.java
* @Description: basic layer class for urso avg
* @author Hundoy - Zohar  
* @date 2016年3月30日 下午4:12:12
* @version V1.0
*/
package com.urso.avg.graphics;

import com.badlogic.gdx.math.Vector3;
import com.urso.avg.UrsoAvgGame;

import java.util.HashMap;

public class UrsoLayer implements Comparable<UrsoLayer> {
	protected UrsoAvgGame game;
	
	protected String uname;
	protected boolean isVisible = true;
	protected float opacity = 1f;
	protected Vector3 pos = new Vector3(0, 0, 0);
	protected int priority;
	protected boolean isPainting = false;
	
	private HashMap<String, UrsoLayer> children;
	private UrsoLayer parent;
	
	// temp
	protected Vector3 tempVec3 = new Vector3();
	
	public UrsoLayer(UrsoAvgGame game, String uname){
		this.game = game;
		this.uname = uname;
	}
	
	public void addChild(UrsoLayer layer){
		if (children==null){
			children = new HashMap<String, UrsoLayer>();
		}
		children.put(layer.uname, layer);
	}

	
	// get the actual position for sprite according to the parents position
	public Vector3 getActualPos(){
		UrsoLayer layer = this;
		tempVec3 = pos.cpy();
		while(layer.parent!=null){
			layer = layer.parent;
			tempVec3 = tempVec3.add(layer.pos);
		}
		return tempVec3;
	}
	
	public void beforePaint() {
		isPainting = true;
	}
	
	public void afterPaint() {
		isPainting = false;
	}
	
	public void paint() {
		
	}
	
	public void dispose(){
		
	}
	
	@Override
	public int compareTo(UrsoLayer other) {
		return this.priority - other.priority;
	}
	
	// getters and setters
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

	public boolean isPainting() {
		return isPainting;
	}
}
