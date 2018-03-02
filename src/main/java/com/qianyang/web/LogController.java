package com.qianyang.web;

import com.qianyang.common.LogHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/log")
public class LogController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@RequestMapping("write")
	public String write(){
		logger.info("this is a info log");
		logger.warn("this is a warn log");
		logger.debug("this is a debug log");
		logger.error("this is a error log");
		
		new LogHelper().writeLog();
		
		return "ok";
	}
}
//默认情况下不会打印debug级别的日志
//在application.properties 控制日志级别

//http://blog.csdn.net/sun_t89/article/details/52130839
