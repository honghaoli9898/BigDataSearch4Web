package com.tl.job002.manager;

import java.util.Map;

import org.apache.log4j.Logger;

import com.sleepycat.je.Database;
import com.tl.job002.bdb.load.BdbDataLoad;
import com.tl.job002.server.bdb.BdbQuery;
import com.tl.job002.utils.FileOperatorUtil;
import com.tl.job002.utils.StaticValue;
import com.tl.job002.utils.SystemParas;

public class BdbManager {
	private static Logger logger = org.apache.log4j.Logger.getLogger(BdbManager.class);
	private static BdbQuery bdbQuery = null;
	static {
		try {
			String max_rootDir = FileOperatorUtil.getMaxFirstLevelFileName(SystemParas.rootDir,
					StaticValue.whole_number_regex);
			BdbManager.init(max_rootDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void init(String rootDir) throws Exception {
		logger.info("本次索引数据的根目录为="+rootDir);
		long start_ts = System.currentTimeMillis();
		Map<String, Database> mainMap = BdbDataLoad.getMainMap(rootDir);
		long end_ts = System.currentTimeMillis();
		System.out.println("初始化用时=" + (end_ts - start_ts) / 1000 + "秒");
		bdbQuery = new BdbQuery(mainMap);
		StaticValue.currentMaxDateRootDir=rootDir;
	}

	public static String getQueryResult(String mid) {
		try {
			return bdbQuery.getQuery(mid);
		} catch (Exception e) {
			e.printStackTrace();
			return "查询出现异常，请通知研发人员!";
		}
	}
}
