package online.zzy.springbootmybatis.dao;

import online.zzy.springbootmybatis.beans.Authority;
import online.zzy.springbootmybatis.beans.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zzy
 * @description shiro dao
 * @date 2019/6/26
 */
@Repository
public interface ShiroDao {
    User getPasswordByUsername(@Param("username") String username) ;

    Authority getAuthorityByUsername(@Param("username1")String username);

    Integer addUser(@Param("user") User user);

    boolean addAuthority(@Param("userid1") Integer userid, @Param("username2") String username);
}
