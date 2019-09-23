package online.zzy.springbootmybatis.exception;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zzy
 * @description shiro认证
 * @date 2019/7/17
 */
@ControllerAdvice
public class ShiroException {

    @ExceptionHandler(AuthenticationException.class)
    public String authenticationException(HttpServletRequest request){
        request.setAttribute("error", "登陆失败");
        System.out.println("登陆失败");
        return "error";
    }
    @ResponseBody
    @ExceptionHandler(IncorrectCredentialsException.class)
    public String incorrectCredentialsException(HttpServletRequest request){
        request.setAttribute("error", "密码错误");
        return "error";
    }


}
