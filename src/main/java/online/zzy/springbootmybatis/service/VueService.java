package online.zzy.springbootmybatis.service;

import online.zzy.springbootmybatis.beans.Product;

public interface VueService {
    String getProdList();

    String addProduct(Product product);
}
