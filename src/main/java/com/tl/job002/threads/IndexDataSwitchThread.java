package com.tl.job002.threads;

import java.util.List;

import org.apache.log4j.Logger;

import com.tl.job002.manager.BdbManager;
import com.tl.job002.utils.FileOperatorUtil;
import com.tl.job002.utils.StaticValue;
import com.tl.job002.utils.SystemParas;

public class IndexDataSwitchThread extends Thread {
	// 首次进入服务切换时需要等待一个周期,因为首次提供服务时初始化交给了主线程
	private static Logger logger = Logger.getLogger(IndexDataSwitchThread.class);
	private String name;
	public static boolean runnableEnable = true;

	public IndexDataSwitchThread(String name) {
		super(name);
		this.name = name;
	}

	public static void init() {
		new IndexDataSwitchThread("索引服务切换线程").start();
	}

	@Override
	public void run() {
		int once_chech_interval_time = 1;
		while (runnableEnable) {
			try {
				logger.info("索引切换线程,将睡眠" + once_chech_interval_time + "分钟");
				Thread.sleep(once_chech_interval_time * 60 * 1000);
				logger.info("索引切换线程,将睡眠结束!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			String newMaxDateRootDir = FileOperatorUtil.getMaxFirstLevelFileName(SystemParas.rootDir,
					StaticValue.whole_number_regex);
			if (StaticValue.currentMaxDateRootDir.equals(newMaxDateRootDir)) {
				// 如果相等，则代表数据无变化
				logger.info("索引数据无更新，不需要更新索引服务,将等待下一个check周期!");
			} else {
				List<String> allFileNameList = FileOperatorUtil
						.getAllFileName(newMaxDateRootDir);
				System.out.println(allFileNameList.size());
				if (allFileNameList.contains("_SUCCESS")) {
					try {
						logger.info("索引数据有更新，将加载新索引数据!");
						BdbManager.init(newMaxDateRootDir);
						logger.info("索引数据更新完成，服务已更新!");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
