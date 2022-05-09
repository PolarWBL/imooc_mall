package com.imooc.mall.service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.request.AddCategoryReq;
import com.imooc.mall.model.request.UpdateCategoryReq;
import com.imooc.mall.model.vo.CategoryVO;

import java.util.List;

/**
 * @author Boliang Weng
 */
public interface CategoryService {

    /**
     *  新增商品类别
     * @param addCategoryReq 新增的商品的信息类
     */
    void add(AddCategoryReq addCategoryReq);

    /**
     *  更新目录
     * @param updateCategoryReq 目录
     *
     */
    void update(UpdateCategoryReq updateCategoryReq);

    /**
     *  删除目录
     * @param id 目录id
     */
    void delete(Integer id);

    /**
     *  获取所有商品目录列表
     * @param pageNum 页号
     * @param pageSize 页大小
     * @return 返回PageInfo类型
     */
    PageInfo<Category> listCategoryForAdmin(Integer pageNum, Integer pageSize);

    /**
     *  获取所有商品目录列表(递归)
     * @param parentId 父目录id
     * @return List<CategoryVO>
     */
    List<CategoryVO> listCategoryForCustomer(Integer parentId);
}
