package com.qianyang.service.impl;

import com.qianyang.domain.User;
import com.qianyang.domain.UserRepository;
import com.qianyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
    private UserRepository userRepository;
	 
	@Override
	public void create(String name, Integer age) {
		jdbcTemplate.update("insert into USER(NAME, AGE) values(?, ?)", name, age);
	}

	@Override
	public void deleteByName(String name) {
		jdbcTemplate.update("delete from USER where NAME = ?", name);
	}

	@Override
	public Integer getAll() {
		return jdbcTemplate.queryForObject("select count(1) from USER", Integer.class);
	}

	@Override
	public void deleteAll() {
		jdbcTemplate.update("delete from USER");
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void createUserRropagationRequired(String naem, Integer age) {
		//REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
		userRepository.save(new User(naem, age));
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public void createUserRropagationSupports(String naem, Integer age) {
		//SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
		userRepository.save(new User(naem, age));
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void createUserRropagationRequiredNew(String naem, Integer age) {
		//REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
		userRepository.save(new User(naem, age));
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void createUserRropagationNOT_SUPPORTED(String naem, Integer age) {
		//NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
		userRepository.save(new User(naem, age));
	}

	@Override
	@Transactional(propagation=Propagation.NESTED)
	public void createUserRropagationNESTED(String naem, Integer age) {
		//NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于REQUIRED
		userRepository.save(new User(naem, age));
	}

	//https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html
}
