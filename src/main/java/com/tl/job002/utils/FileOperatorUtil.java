package com.tl.job002.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileOperatorUtil {
	public static boolean createRootDir(String dirPath){
		File file=new File(dirPath);
		return file.mkdirs();
	}
	public static boolean deleteDirectory(String dirPath){
		File dirFileObj=new File(dirPath);
		if(dirFileObj.isDirectory()){
			File[] fileArray=dirFileObj.listFiles();
			if(fileArray.length==0){
				return dirFileObj.delete();
			}
			else{
				for (File subFileObj : fileArray) {
					if(subFileObj.isFile()){
						subFileObj.delete();
					}
					else{
						deleteDirectory(subFileObj.toString());
					}
				}
			}
			return dirFileObj.delete();
		}
		return false;
	}
	public static List<String> getFirstLevelFileNameList(String rootDir,String fileterRegex){
		File fileObj = new File(rootDir);
		File[] fileArray = fileObj.listFiles();
		List<String> resultList = new ArrayList<String>();
		for (File tempFile : fileArray) {
			String tempFileName = tempFile.getName();
			if(tempFile.isDirectory()){
				if(StringOperatorUtil.isNotBlank(fileterRegex)){
					if(tempFileName.matches(fileterRegex)){
						resultList.add(tempFileName);
					}
				}else{
					resultList.add(tempFileName);
				}
			}
		}
		return resultList;
	}
	public static List<String> getAllFileName(String rootDir){
		List<String> fileNameList=new ArrayList<String>();
		File file=new File(rootDir);
		if(!file.exists()){
			return null;
		}
		if(file.isDirectory()){
			File[] fileArray=file.listFiles();
			for (File file2 : fileArray) {
				fileNameList.addAll(getAllFileName(file2.toString()));
			}
		}else{
			fileNameList.add(file.getName());
		}
		return fileNameList;
	}
	public static String getMaxFirstLevelFileName(String rootDir,String fileterRegex){
		List<String> list=getFirstLevelFileNameList(rootDir,fileterRegex);
		Collections.sort(list);
		String max_file=list.get(list.size()-1);
		return rootDir+"/"+max_file;
	}
	public static void main(String[] args) {
		String rootDir="D:/test";
		List<String> s=getAllFileName(rootDir+"/"+"20181021");
		System.out.println(s.contains("_SUCCESS"));
	}
}
