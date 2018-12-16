package com.tl.job002.bdb.load;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sleepycat.je.Database;
import com.tl.job002.utils.BDBOperatorUtil;
import com.tl.job002.utils.FileOperatorUtil;
import com.tl.job002.utils.StaticValue;

public class BdbDataLoad {
	public static String databaseName = "weibo";
	public static Map<String, Database> getMainMap(String rootDir) {
		Map<String, Database> mainMap = new HashMap<String, Database>();
		List<String> dirNameList = FileOperatorUtil.getFirstLevelFileNameList(rootDir, StaticValue.whole_number_regex);
		for (String dirName : dirNameList) {
			String indexDirPath = rootDir + "/" + dirName;
			Database indexObj = BDBOperatorUtil.getDB(indexDirPath, databaseName);
			mainMap.put(dirName, indexObj);
		}
		// 返回构造完成的mainMap对象
		return mainMap;
	}

	public static void main(String[] args) {
		String rootDir = "D:/test/0";
		Map<String, Database> map = getMainMap(rootDir);
		System.out.println(map);
	}
}
