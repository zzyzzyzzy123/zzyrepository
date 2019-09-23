package online.zzy.springbootmybatis.controller;

import online.zzy.springbootmybatis.service.TestService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    TestService service;

    @RequestMapping("/test")
    public String test(){
        return  service.getDept("1");

    }


}
