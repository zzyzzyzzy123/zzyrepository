package online.zzy.springbootmybatis.pool;

/**
 * @author zzy
 * @description 通用工具类
 * @date 2019/7/1
 */
public class BasicUtil {

    public static boolean isNullString(String str){
        if("".equals(str)||null==str){
            return false;
        }else {
            return true;
        }
    }
}
