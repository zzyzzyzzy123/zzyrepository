package online.zzy.springbootmybatis.service;


import online.zzy.springbootmybatis.dao.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    DeptMapper deptMapper;

    public String getDept(String id){
      return  deptMapper.selectDeptById(id);
    }
}
