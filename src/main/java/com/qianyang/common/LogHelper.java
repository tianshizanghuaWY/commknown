package com.qianyang.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHelper {

	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	public void writeLog(){
		logger.info("this is a info log");
		logger.warn("this is a warn log");
		logger.debug("this is a debug log");
		logger.error("this is a error log");
	}
}
