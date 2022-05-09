package com.imooc.mall.service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.pojo.Product;
import com.imooc.mall.model.request.*;
import com.imooc.mall.model.vo.CategoryVO;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author Boliang Weng
 */
public interface ProductService {

    /**
     *  新增商品类别
     * @param addProductReq 新增的商品的信息类
     */
    void add(AddProductReq addProductReq);

    /**
     *  更新商品
     * @param updateProductReq 商品信息
     *
     */
    void update(UpdateProductReq updateProductReq);

    /**
    *  删除商品
    * @param id 商品id
    */
    void delete(Integer id);

    /**
     *  (批量) 上架/下架商品
     * @param ids 商品id数组
     * @param sellStatus 商品状态
     */
    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    /**
     *  获取商品列表
     * @param pageNum 页数
     * @param pageSize 页大小
     * @return pageInfo
     */
    PageInfo<Product> listProductForAdmin(Integer pageNum, Integer pageSize);

    /**
     * 获取商品详细信息
     * @param id 商品id
     * @return 商品
     */
    Product detail(Integer id);

    /**
     *  获取商品列表(根据各种参数排序, 筛选)
     * @param listProductReq 各种参数的集合
     * @return 返回PageInfo类型
     */
    PageInfo<Product> listProductForCustomer(ListProductReq listProductReq);
}
