package com.lofter.utils;

import org.apache.log4j.Logger;





public class YcUtils {
	
	public static Logger logger=Logger.getLogger(YcUtils.class);
	private static StringBuffer transferException(Exception e){
		StringBuffer sb=new StringBuffer();
		for(StackTraceElement ste:e.getStackTrace()){
			sb.append(ste.toString()+"\n");
		}
		return sb;
	}
	public static void error(Exception e){
		
		logger.error(transferException(e));
	}

	public static void info(Exception e){
		
		logger.info(transferException(e));
	}
	public static void warn(Exception e){
		
		logger.warn(transferException(e));
	}
	
}
