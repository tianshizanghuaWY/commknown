package com.qianyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//SpringBootApplication 标明程序的入口
@SpringBootApplication
public class CommknownApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommknownApplication.class, args);
	}

	/*
	@SpringBootApplication开启了Spring的组件扫描和springboot的自动配置功能，相当于将以下三个注解组合在了一起

（1）@Configuration：表名该类使用基于Java的配置,将此类作为配置类

（2）@ComponentScan：启用注解扫描

（3）@EnableAutoConfiguration：开启springboot的自动配置功能
	*/
}
