package online.zzy.springbootmybatis.serviceimpl;

import online.zzy.springbootmybatis.beans.Product;
import online.zzy.springbootmybatis.service.VueService;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zzy
 * @description vueseervice实现类
 * @date 2019/7/12
 */
@Service
public class VueServiceImpl implements VueService {
    @Override
    public String getProdList() {
        /** {id: 1, name: '面包', date: new Date()},
         {id: 2, name: '口香糖', date: new Date()}*/
        List<Map<String ,String>> maps = new ArrayList<>();
        for (int i=1;i<5;i++) {
            Map<String,String> map = new  HashMap();
            map.put("id", i+"");
            map.put("name", "prod"+i);
            map.put("date",new Date().toString());
            maps.add(map);
        }
        JSONArray jsonArray=new JSONArray(maps);
        return jsonArray.toString();
    }

    @Override
    public String addProduct(Product product) {
        System.out.println(product);
        return "ok";
    }
}
