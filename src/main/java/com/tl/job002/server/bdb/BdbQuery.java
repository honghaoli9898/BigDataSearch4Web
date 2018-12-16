package com.tl.job002.server.bdb;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.LockMode;
import com.tl.job002.bdb.load.BdbDataLoad;
import com.tl.job002.utils.HashFuncUtil;

@Component
public class BdbQuery {
	public Map<String, Database> mainMap = null;

	public BdbQuery() {

	}

	public BdbQuery(Map<String, Database> maiMap) {
		this.mainMap = maiMap;
	}

	public String getQuery(String id) throws Exception {
		String ret_message = "not found";
		String bdb_data_dirname = "" + HashFuncUtil.getHashCode(id, 10);
		if (mainMap.containsKey(bdb_data_dirname)) {
			DatabaseEntry keyEntry = new DatabaseEntry(id.getBytes("utf-8"));
			DatabaseEntry dataEntry = new DatabaseEntry();
			mainMap.get(bdb_data_dirname).get(null, keyEntry, dataEntry, LockMode.DEFAULT);
			if (dataEntry != null && dataEntry.getData() != null) {
				ret_message = new String(dataEntry.getData(), "utf-8");
			}
		}
		return ret_message;
	}

	public static void main(String[] args) throws Exception {
		String rootDir = "D:/test/0";
		String id = "m601fDzQ2s";
		Map<String, Database> mainMap = BdbDataLoad.getMainMap(rootDir);
		BdbQuery queryManager = new BdbQuery(mainMap);
		String ret_message = null;
		long start_ts = System.currentTimeMillis();
		ret_message = queryManager.getQuery(id);
		long end_ts = System.currentTimeMillis();
		System.out.println("查询用时=" + (end_ts - start_ts) + "豪秒");
		System.out.println(ret_message);
	}
}
