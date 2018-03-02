package com.qianyang.web;

import com.qianyang.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/*
 * 构建Restful API
 */
@RestController
@RequestMapping(value="/user")
public class UserController {

	// thead-safe Map 
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<User> getUserList(){
    	//handle request: "/user/"
    	//also can use @RequestParam to set query param
    	List<User> r = new ArrayList<User>(users.values());
    	return r;
    }
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postUser(@ModelAttribute User user){
    	//处理 "/user/" 的POST 请求，用来创建user
        users.put(user.getId(), user);
    	return "success";
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user){
    	//处理 "/user/{id}" 的put 请求，用来更新user
    	User u = users.get(id);
    	u.setName(user.getName());
    	
        users.put(id, u);
    	return "success";
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id){
    	//用来处理 "/user/{id}"的 DELETE 请求，用来删除user
    	users.remove(id);
    	return "success";
    }
    
    //RequestMethod.GET 相当于GET查询
    //RequestMethod.POST 相当于新增
    //RequestMethod.PUT 相当于修改
    //RequestMethod.DELETE 相当于删除
}
