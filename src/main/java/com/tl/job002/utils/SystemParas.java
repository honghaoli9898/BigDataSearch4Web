package com.tl.job002.utils;


public class SystemParas {
	public static String configPath = "application.properties";
	public static ReadConfigUtil readConfig = null;
	static {
		try {
			readConfig = new ReadConfigUtil(configPath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public static String rootDir=readConfig.getValue("bdb_root_dir");
}
