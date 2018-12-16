package com.tl.job002.utils;

public class StringOperatorUtil {
	public static boolean isNotBlank(String arg){
		if(arg!=null && arg.trim().length()!=0){
			return true;
		}
		return false;
	}
}
