package com.autotest.extentreport;

public class StringUtil {
	
	public static boolean isNotEmpty(String str) {
		return null != str && !"".equals(str);
	}

	public static boolean isEmpty(String str) {
		return null == str || "".equals(str);
	}
	
	/**
	 * 
	 * @param sourceStr ´ýÌæ»»×Ö·û´®
	 * @param matchStr  Æ¥Åä×Ö·û´®
	 * @param replaceStr  Ä¿±êÌæ»»×Ö·û´®
	 * @return
	 */
	public static String replaceFirst(String sourceStr,String matchStr,String replaceStr){
		int index = sourceStr.indexOf(matchStr);
		int matLength = matchStr.length();
		int sourLength = sourceStr.length();
		String beginStr = sourceStr.substring(0,index);
		String endStr = sourceStr.substring(index+matLength,sourLength);
		sourceStr = beginStr+replaceStr+endStr;
		return sourceStr;
	}
}
