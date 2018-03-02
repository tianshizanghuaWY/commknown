package com.qianyang;


import com.qianyang.domain.User;
import com.qianyang.domain.UserRepository;
import com.qianyang.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(FirstApplication.class)
public class JpaTemplateTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void test(){
		userRepository.save(new User("AA", 10));
		userRepository.save(new User("AA", 100));
		userRepository.save(new User("BB", 20));
		userRepository.save(new User("CC", 30));
		
		Assert.assertEquals(4, userRepository.findAll().size());
		Assert.assertEquals(30, userRepository.findByName("CC").getAge().longValue());
		
		Assert.assertEquals(100, userRepository.findByNameAndAge("AA",100).getAge().longValue());
		
		Assert.assertEquals(20, userRepository.findUser("BB").getAge().longValue());
	}
	/*
	@Test
	@Transactional
	public void transactionTest(){
		userRepository.save(new User("DDDD", 0));
		userRepository.save(new User("DDDDDDD", 0));
	}*/
	
	/*@Test
	public void test1(){
		userService.createUserRropagationRequired("WWW", 0);
		userService.createUserRropagationRequired("WWWWWWWWWW", 0);
		
		//测试结果 第二条回滚了， 第一条并没有回滚
	}*/
	
	/*@Test
	@Transactional
	public void test2(){
		userService.createUserRropagationRequired("WWW", 0);
		userService.createUserRropagationRequired("WWWWWWWWWW", 0);
		
		//测试结果:都回滚了
	}*/
	
	/*@Test
	@Transactional
	public void test3(){
		userRepository.save(new User("XXX", 0)); //1
		userService.createUserRropagationRequiredNew("WWW", 0);//2
		userService.createUserRropagationRequired("WWWWWWWWWW", 0);//3
		//测试结果: 1,3回滚； 2没有回滚
		//分析：2使用 REQUIREDNEW 策略,也就是2在新的事务里执行并提交， 2将已经存在的事务(1创建的事务)挂起
		//3使用1创建的事务,并产生异常，事务回滚
	}*/
	
	/*@Test
	@Transactional
	public void test4(){
		userRepository.save(new User("XXX", 0)); //1
		userService.createUserRropagationNOT_SUPPORTED("WWWWWWWWWW", 0);//2
		userService.createUserRropagationRequired("WWW", 0);//3
		//测试结果: 2 不在事务里运行,但是抛出异常，整个事务回滚
	}*/
	
	/*@Test
	@Transactional
	public void test4(){
		userRepository.save(new User("XXX", 0)); //1
		userService.createUserRropagationNESTED("WWWWWWWWWW", 0);//2
		userService.createUserRropagationRequired("WWW", 0);//3
		//测试结果: 不支持  NESTED
	}*/
	
}
