package com.qianyang.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>
 *
 * @author 千阳
 * @date 2018-03-02
 */
@RestController
public class HelloController {

    //http://localhost:8080/hello
    @RequestMapping("/hello")
    public String hello() {
        return "hello,this is a springboot demo";
    }
}
