package com.my.test;

public class MakeRandom {
	public static String GetRandomPassword() {
		char[] charSet = new char[] {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z'};
		int arr = 0;
		StringBuffer sb = new StringBuffer();
		for (int i=0 ; i<8 ; i++) {
			arr = (int)(charSet.length * Math.random());
			sb.append(charSet[arr]);
		}
		
		return sb.toString();
	}
}
