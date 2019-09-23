package online.zzy.springbootmybatis.controller;

import online.zzy.springbootmybatis.beans.Product;
import online.zzy.springbootmybatis.service.VueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zzy
 * @description vue控制器
 * @date 2019/7/12
 */
@RestController
@RequestMapping("/vue")
public class VueController
{
    @Autowired
    VueService vueServiceImpl;

    @RequestMapping("getprodlist")
    public String getProdList(){

        return vueServiceImpl.getProdList();
    }

    @RequestMapping("/addproduct")
    public String addProduct(@RequestBody Product product){

        return vueServiceImpl.addProduct(product);
    }

    @RequestMapping("/delproduct/{id}")
    @ResponseBody
    public String delProduct(@PathVariable Integer id){
        System.out.println(id);
        return "ok";
    }
}
