package online.zzy.springbootmybatis.service;


import online.zzy.springbootmybatis.beans.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zzy
 * @description shiro service
 * @date 2019/6/26
 */

public interface ShiroService {


    String register(User user, HttpServletRequest request);

    String shiroRolesTest();
}
