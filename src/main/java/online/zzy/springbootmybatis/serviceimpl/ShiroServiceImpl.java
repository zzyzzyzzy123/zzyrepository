package online.zzy.springbootmybatis.serviceimpl;

import online.zzy.springbootmybatis.beans.User;
import online.zzy.springbootmybatis.dao.ShiroDao;
import online.zzy.springbootmybatis.pool.BasicUtil;
import online.zzy.springbootmybatis.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zzy
 * @description shiro serviceimpl
 * @date 2019/6/26
 */
@Service
@EnableTransactionManagement
public class ShiroServiceImpl implements ShiroService{
@Autowired
ShiroDao shiroDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String register(User user, HttpServletRequest request) {

        if(!BasicUtil.isNullString(user.getUsername())||!BasicUtil.isNullString(user.getPassword())){
            request.setAttribute("error", "账号密码不能为空");
            return "error";
        }
        //是否存在此username

        shiroDao.addUser(user);
        System.out.println(user);
        if(shiroDao.addAuthority(user.getUserid(),user.getUsername())){
            return "login";
        }
        return "register";
    }

    //@RequiresRoles(value={"admin"}),只有controller才能调用
    public String shiroRolesTest(){
        Subject subject = SecurityUtils.getSubject();
        boolean b = subject.hasRole("user");
        System.out.println(b);
        System.out.println("调用了admin权限");
        return null;
    }

}
