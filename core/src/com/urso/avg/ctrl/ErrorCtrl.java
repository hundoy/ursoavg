/**  
* @Title: ErrorCtrl.java
* @Description: This class used to handle and display some error or warning for game
* @author Hundoy - Zohar  
* @date 2016年3月30日 下午5:04:39
* @version V1.0  
*/
package com.urso.avg.ctrl;

import com.badlogic.gdx.Gdx;
import com.urso.avg.UrsoAvgGame;

public class ErrorCtrl {
	private UrsoAvgGame game;
	
	private String outputPath = ""; // not implements temporarily
	private StringBuilder allMsg;
	
	public ErrorCtrl(UrsoAvgGame game, String outputPath){
		this.game = game;
		this.outputPath = outputPath;
		allMsg = new StringBuilder();
	}
	
	public void warnFileNotFound(String msg){
		String tag = "[Warning]File not found - ";
		Gdx.app.error(tag, msg);
		allMsg.append(tag).append(msg).append("/n");
	}
	
	public void nullError(String msg){
		String tag = "[Error]NULL value - ";
		Gdx.app.error(tag, msg);
		allMsg.append(tag).append(msg).append("/n");
	}

	public void formatError(String msg){
		String tag = "[Warning]Format error - ";
		Gdx.app.error(tag, msg);
		allMsg.append(tag).append(msg).append("/n");
	}
	
	/**
	 * 
	 */
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
