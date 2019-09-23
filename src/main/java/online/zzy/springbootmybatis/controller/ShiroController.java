package online.zzy.springbootmybatis.controller;


import online.zzy.springbootmybatis.beans.User;
import online.zzy.springbootmybatis.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zzy
 * @description shiro控制器
 * @date 2019/6/25
 */
@Controller
@RequestMapping("/shiro")
public class ShiroController {
    @Autowired
    ShiroService shiroServiceImpl;

    @PostMapping(value = "/login")
    public String login(String username,String password){
        System.out.println(password);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken passwordToken=new UsernamePasswordToken(username,password);
        subject.login(passwordToken);
        passwordToken.setRememberMe(true);
        return "/success";
    }
    @RequestMapping(value = "/register")
    public String register(User user, HttpServletRequest request){
        System.out.println(user);
        return shiroServiceImpl.register(user,request);
    }


    @RequestMapping("index")
    //@RequiresRoles(value={"admin"})
    public String index(){
        shiroServiceImpl.shiroRolesTest();
        return "index";
    }

    @ResponseBody
    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
       // System.out.println(subject.getPrincipal().toString());
        return "logout";
    }
}
