package com.tl.job002.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadConfigUtil {
	private static Properties pp;
	private String configPath;
	private static Logger logger = Logger.getLogger(ReadConfigUtil.class);

	public ReadConfigUtil(String configPath) throws IOException {
		this.configPath = configPath;
		InputStream is = null;
		File configFileObj = null;
		// 优先读取当前目录下的配置文件路径，如果不存在，则读取jar包内部的
		if ((configFileObj = new File(configPath)).exists()) {
			is = new FileInputStream(configFileObj);
			logger.info("从文件路径中读取配置文件!!");
		} else {
			is = ReadConfigUtil.class.getClassLoader().getResourceAsStream(this.configPath);
			logger.info("从jar包中读取配置文件!!");
		}
		Reader reader = new InputStreamReader(is, "utf-8");
		pp = new Properties();
		pp.load(reader);
		reader.close();
	}

	public String getValue(String key) {
		return pp.getProperty(key);
	}
}
