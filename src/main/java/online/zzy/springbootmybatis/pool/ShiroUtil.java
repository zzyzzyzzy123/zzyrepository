package online.zzy.springbootmybatis.pool;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author zzy
 * @description shiro工具类
 * @date 2019/6/27
 */
public class ShiroUtil {
    public static Object saltMd5(String hashAlgorithmName,Object salt ,Object credentials){
        int hashIterations=1024;
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        return simpleHash;
    }


}
