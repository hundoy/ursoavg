/**  
* @Title: DicBean.java
* @Description: a dictionary class like a string->string hashmap
* @author Hundoy - Zohar  
* @date 2016年4月4日 下午8:21:34
* @version V1.0  
*/
package com.zohar.common.bean;

import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;

import static com.zohar.common.util.ToolUtil.*;

/**
 * @author zohar
 *
 */
public class DicBean {
	private HashMap<String, String> map;
	
	public DicBean(){
		map = new HashMap<String, String>();
	}
	
	/**
	 * @param str k1=v1 k2=v2 k3=v3... split with space
	 */
	public DicBean(String str) {
		this();
		
		if (isnob(str)){
			String[] arr = str.split(" ");
			if (isnoe(arr)){
				for (String arrstr : arr){
					if (arrstr.indexOf("=")>-1){
						String[] kv = arrstr.split("=");
						if (isnoe(kv) && kv.length>=2){
							String k = kv[0].trim().toLowerCase();
							String v = kv[1].trim();
							if (isnob(k)){
								map.put(k, v);
							}
						}
					}
				}
			}
		}
	}

	public void put(String k, String v){
		if (isnob(k)){
			String lowk = k.toLowerCase();
			if (v==null) v = "";
			map.put(lowk, v);
		}
	}
	
	public void put(String k, int v){
		put(k, String.valueOf(v));
	}
	
	public void put(String k, float v){
		put(k, String.valueOf(v));
	}
	
	public String get(String k){
		if (isnob(k)){
			String lowk = k.toLowerCase();
			if (map.containsKey(lowk)){
				return map.get(lowk);
			} else{
				return "";
			}
		}
		return "";
	}
	
	public int getInt(String k){
		String str = get(k);
		if (isnob(str)){
			return Integer.valueOf(str);
		}
		return 0;
	}
	
	public float getFloat(String k){
		String str = get(k);
		if (isnob(str)){
			return Float.valueOf(str);
		}
		return 0;
	}
	
	public boolean getBool(String k){
		String str = get(k);
		if (isnob(str)){
			return str.equalsIgnoreCase("true");
		}
		return false;
	}
	
	public boolean have(String k){
		if (isnob(k)){
			return map.containsKey(k.toLowerCase());
		}
		return false;
	}

	public Rectangle getRect(String k) {
		String str = get(k);
		if (isnob(str)){
			String[] rects = str.split(",");
			if (rects.length>=4){
				return new Rectangle(Float.parseFloat(rects[0]), Float.parseFloat(rects[1]), Float.parseFloat(rects[2]), Float.parseFloat(rects[3]));
			}
		}
		return null;
	}
}
