package com.qianyang.web;

import com.qianyang.Exception.ErrorInfo;
import com.qianyang.Exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@RequestMapping(value="/exc")
public class ExceptionController {

	Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@RequestMapping("/page")
	public String page() throws Exception{
		genException(1);
		
		return "success";
	}
	
	@RequestMapping("/page2")
	public String page2() throws Exception{
		//测试异常被捕捉后, 不再走统一异常处理流程
		try{
			genException(1);
		}catch(Exception e){
			logger.error("page2 catch exception");
		}
		
		return "success";
	}
	
	@RequestMapping("/page3")
	@ResponseBody
	public void page3(HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		genException(1);
		
		PrintWriter witer = response.getWriter();
		ErrorInfo<String> error = new ErrorInfo<String>();
		error.setCode(ErrorInfo.OK);
		error.setData("page3");
		error.setUrl(request.getRequestURL().toString());
		
		witer.write(com.alibaba.fastjson.JSONObject.toJSONString(error));
	}
	
	@RequestMapping("/json")
	public String json() throws Exception{
		//如果统一异常处理没有定义 MyException 的统一异常处理, 并且这里也没有捕捉异常，
		//那么统一异常处理流程按照 Exception 去处理
		genException(2);
		
		return "success";
	}
	
	private void genException(int e)throws Exception{
		if(e == 1){
			throw new Exception("Exception occur");
		}else {
			throw new MyException("MyException occur");
		}
	}
}
