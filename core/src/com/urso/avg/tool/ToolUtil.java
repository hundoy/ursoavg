/**  
* @Title: ToolUtil.java
* @Description: A tool class with some convenient methods
* @author Hundoy - Zohar  
* @date 2016年3月30日 下午4:38:19
* @version V1.0  
*/
package com.urso.avg.tool;

public class ToolUtil {
	
	/**
	 * check whether a string is not null or blank
	 * @return
	 */
	public static boolean isnnb(String str){
		return !(str==null || str.length()==0);
	}
	
	public static boolean isnne(Object[] arr){
		return !(arr==null || arr.length==0);
	}
}
