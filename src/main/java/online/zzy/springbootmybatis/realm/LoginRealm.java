package online.zzy.springbootmybatis.realm;

import online.zzy.springbootmybatis.beans.Authority;
import online.zzy.springbootmybatis.beans.User;
import online.zzy.springbootmybatis.dao.ShiroDao;
import online.zzy.springbootmybatis.pool.ShiroUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zzy
 * @description 登陆realm
 * @date 2019/6/26
 */
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    ShiroDao shiroDao;

    /**
     * 功能描述
     *
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @author zzy
     * @date 2019/6/26
     * @description 添加权限
     * param  * @param principals
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("doGetAuthorizationInfo");
    //1.PrincipalCollection中获取用户登陆信息
        Object primaryPrincipal = principals.getPrimaryPrincipal();

        //2.利用登陆信息来判断当前用户的权限
        System.out.println("doGetAuthorizationInfo  " + primaryPrincipal);
        Authority authority = shiroDao.getAuthorityByUsername((String) primaryPrincipal);
        System.out.println(authority);
        //3.创建simpleauthorizationInfo,验证roles
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo(authority.getRoles());
        //4.返回simpleAuthorizationInfo对象
        authenticationInfo.setStringPermissions(authority.getPermissions());
        return authenticationInfo;
    }

    /**
     * 功能描述
     *
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author zzy
     * @date 2019/6/26
     * @description 登陆
     * param  * @param token
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //1.AuthenticationToken转换为usernamepasswoToken
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;

        //2.从usernamepasswordToken中获取username
        String username = passwordToken.getUsername();

        //3.调用数据库方法，查询username对应账户
        User user = shiroDao.getPasswordByUsername(username);

        //4.用户不存在可输出unknownAccountException
        if (user == null) {
            throw new UnknownAccountException("用户不存在！");
        }else {
            System.out.println(user);
        }

        System.out.println("doGetAuthenticationInfo");
        //5.根据用户信息，决定是否抛出authenticationException
        if ("monster".equals(username)) {
            throw new AuthenticationException("用户被锁定！");
        }


        //6.根据用户情况构建AuthenticationInfo对象并返回,通常使用实现类为simpleAuthencationInfo
        //以下信息从数据库获取
        //1).principal:认证的实体信息，可以是username，也可以是数据表对应的实体对象。
        Object principal = username;

        //2).credentials密码,从数据库取出来的
        Object credentials = ShiroUtil.saltMd5("MD5", user.getUsername(), user.getPassword());
        System.out.println(credentials);
        //3).realmname:当前realm对象的name，调用父类的getName()方法即可
        String realmName = getName();

        //4).盐值
        ByteSource credentialSalt = ByteSource.Util.bytes(username);
        //SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(principal,credentials,realmName);


        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal, credentials, credentialSalt, realmName);
        return simpleAuthenticationInfo;
    }
}
