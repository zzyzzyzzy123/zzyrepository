package online.zzy.springbootmybatis.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptMapper {
   //@Select("select departmentname from dept where id=#{id}")
    public String selectDeptById(String id);
}
