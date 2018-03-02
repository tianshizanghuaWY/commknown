package com.qianyang.service;

public interface UserService {

	public void create(String name, Integer age);
	public void deleteByName(String name);
	public Integer getAll();
	public void deleteAll();
	
	
	//以下用来测试事务的接口
	public void createUserRropagationRequired(String naem, Integer age);
	public void createUserRropagationSupports(String naem, Integer age);
	public void createUserRropagationRequiredNew(String naem, Integer age);
	public void createUserRropagationNOT_SUPPORTED(String naem, Integer age);
	public void createUserRropagationNESTED(String naem, Integer age);
}
