package com.qianyang.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
 * springDataJPA study
 * SpringDataJPA内部使用了类代理的方式让继承了它接口的子接口都以spring管理的Bean的形式存在，
 * 也就是说我们可以直接使用@Autowired注解在spring管理bean使用， 
 * 
 * JpaSpecificationExecutor SpringDataJPA 提供的复杂查询接口
 */

//不使用这个注解也可以自动注入到spring 容器中
//@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

	User findByName(String name);
	
	//还必须是类似的名字 findByNameAndAge， 用findNameAndAge 直接报错
	User findByNameAndAge(String name, Integer age);
	
	@Query("from User where name=:name")
	User findUser(@Param("name") String name);
}
