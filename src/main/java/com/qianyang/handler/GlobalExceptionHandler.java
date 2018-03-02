package com.qianyang.handler;

import com.qianyang.Exception.ErrorInfo;
import com.qianyang.Exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@ControllerAdvice
public class GlobalExceptionHandler {

	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	private static final String DEFAULT_ERROR_VIEW = "error";
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultHandler(HttpServletRequest request, 
			HttpServletResponse response,
			Exception e) throws Exception{
		//判断请求是 ajax json请求
		if (request.getHeader("accept").indexOf("application/json") > -1
	             || (request.getHeader("X-Requested-With")!= null 
	             && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1)){
			logger.info("handler exception of application/json");
			
			PrintWriter witer = response.getWriter();
			ErrorInfo<String> error = new ErrorInfo<String>();
			error.setCode(ErrorInfo.ERROR);
			error.setData("exception occur in:" + request.getRequestURL());
			error.setMessage(e.getMessage());
			error.setUrl(request.getRequestURL().toString());
			
			witer.write(com.alibaba.fastjson.JSONObject.toJSONString(error));
			return null;
		}else{
			ModelAndView model = new ModelAndView("error");
			
			model.addObject("exception", e);
			model.addObject("url", request.getRequestURL());
			model.setViewName(DEFAULT_ERROR_VIEW);
	        
			return model;
		}
	}
	
	@ExceptionHandler(value = MyException.class)
	@ResponseBody   //表明返回json字符串
	public ErrorInfo<String> myExcetionHandler(HttpServletRequest req, 
			MyException e) throws Exception{
		
		ErrorInfo<String> error = new ErrorInfo<String>();
		error.setCode(ErrorInfo.ERROR);
		error.setData("exception occur in:" + req.getRequestURL());
		error.setMessage(e.getMessage());
		error.setUrl(req.getRequestURL().toString());
		
		return error;
	}
}
