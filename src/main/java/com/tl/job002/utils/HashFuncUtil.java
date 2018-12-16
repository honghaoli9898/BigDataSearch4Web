package com.tl.job002.utils;

public class HashFuncUtil {
	public static int getHashCode(String mid,int reduceNum){
		return (mid.hashCode() & Integer.MAX_VALUE) % reduceNum;
	}
	public static void main(String[] args) {
		System.out.println(getHashCode("mEmo8fEziz",10));
	}
}
