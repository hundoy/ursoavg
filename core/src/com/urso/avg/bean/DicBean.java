/**  
* @Title: DicBean.java
* @Description: a dictionary class like a string->string hashmap
* @author Hundoy - Zohar  
* @date 2016年4月4日 下午8:21:34
* @version V1.0  
*/
package com.urso.avg.bean;

import java.util.HashMap;

import static com.urso.avg.tool.ToolUtil.*;

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
	 * @param str
	 */
	public DicBean(String str) {
		this();
		
		if (isnnb(str)){
			String[] arr = str.split(" ");
			if (isnne(arr)){
				for (String arrstr : arr){
					if (arrstr.indexOf("=")>-1){
						String[] kv = arrstr.split("=");
						if (isnne(kv) && kv.length>=2){
							String k = kv[0].trim().toLowerCase();
							String v = kv[1].trim();
							if (isnnb(k)){
								map.put(k, v);
							}
						}
					}
				}
			}
		}
	}

	public void put(String k, String v){
		if (isnnb(k)){
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
		if (isnnb(k)){
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
		if (isnnb(str)){
			return Integer.valueOf(str);
		}
		return 0;
	}
	
	public float getFloat(String k){
		String str = get(k);
		if (isnnb(str)){
			return Float.valueOf(str);
		}
		return 0;
	}
	
	public boolean getBool(String k){
		String str = get(k);
		if (isnnb(str)){
			return str.equalsIgnoreCase("true");
		}
		return false;
	}
	
	public boolean have(String k){
		if (isnnb(k)){
			return map.containsKey(k.toLowerCase());
		}
		return false;
	}
}
