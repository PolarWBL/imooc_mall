package com.imooc.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mall.common.Constant;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.dao.CategoryMapper;
import com.imooc.mall.model.dao.ProductMapper;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.pojo.Product;
import com.imooc.mall.model.query.ListProductQuery;
import com.imooc.mall.model.request.*;
import com.imooc.mall.model.vo.CategoryVO;
import com.imooc.mall.service.CategoryService;
import com.imooc.mall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Boliang Weng
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private CategoryService categoryService;

    @Override
    public void add(AddProductReq addProductReq){
        Product product = new Product();
        BeanUtils.copyProperties(addProductReq, product);
        Product productOld = productMapper.selectByName(product.getName());

        if (productOld != null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }

        int count = productMapper.insertSelective(product);
        if (count != 1) {
            throw new ImoocMallException(ImoocMallExceptionEnum.CREATE_FAILED);
        }
    }


    @Override
    public void update(UpdateProductReq updateProductReq) {
        Product product = new Product();
        BeanUtils.copyProperties(updateProductReq, product);
        Product productOld = productMapper.selectByName(product.getName());

        //名称相同且不是自己(id不同), 不能修改
        if (productOld != null && !productOld.getId().equals(product.getId())) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }

        int count = productMapper.updateByPrimaryKeySelective(product);
        if (count != 1) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }
    }


    @Override
    public void delete(Integer id) {
        Product productOld = productMapper.selectByPrimaryKey(id);
        //查不到商品信息
        if (productOld == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.DELETE_FAILED);
        }
        int count = productMapper.deleteByPrimaryKey(id);
        if (count != 1) {
            throw new ImoocMallException(ImoocMallExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public void batchUpdateSellStatus(Integer[] ids, Integer sellStatus){

        if (sellStatus == null || ids == null || ids.length == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }

        int count = productMapper.batchUpdateSellStatus(ids, sellStatus);
        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public PageInfo<Product> listProductForAdmin(Integer pageNum, Integer pageSize) {
        //分类列表（平铺）
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productMapper.selectListForAdmin();
        return new PageInfo<>(productList);
    }

    @Override
    public Product detail(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);

        if (product == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NO_DETAIL);
        }

        return product;
    }

    @Override
    public PageInfo<Product> listProductForCustomer(ListProductReq listProductReq) {
        //构建Query对象
        ListProductQuery listProductQuery = new ListProductQuery();

        //搜索处理, 如果不为空
        if (!StringUtils.isEmpty(listProductReq.getKeyword())) {
            String keyword = "%" + listProductReq.getKeyword() + "%";
            listProductQuery.setKeyword(keyword);
        }

        //目录处理: 查目录下的商品, 还要查找其子目录下的商品
        if (listProductReq.getCategoryId() != null) {
            List<CategoryVO> categoryVOList = categoryService.listCategoryForCustomer(listProductReq.getCategoryId());
            //展开分类, 不要递归类型
            List<Integer> categoryIds = new ArrayList<>();
            categoryIds.add(listProductReq.getCategoryId());
            getCategoryIds(categoryVOList, categoryIds);

            listProductQuery.setCategoryIds(categoryIds);
        }

        //排序处理
        String orderBy = listProductReq.getOrderBy();
        if (Constant.ProductListOrderBy.PRICE_ASE_DESC.contains(orderBy)) {
            PageHelper.startPage(listProductReq.getPageNum(), listProductReq.getPageSize(), orderBy);
        }else {
            PageHelper.startPage(listProductReq.getPageNum(), listProductReq.getPageSize());
        }

        List<Product> productList = productMapper.selectListForCustomer(listProductQuery);
        return new PageInfo<>(productList);

    }

    private void getCategoryIds(List<CategoryVO> categoryVOList, List<Integer> categoryIds){
        for (CategoryVO categoryVO : categoryVOList) {
            if (categoryVO.getChildCategory() != null) {
                categoryIds.add(categoryVO.getId());
                getCategoryIds(categoryVO.getChildCategory(), categoryIds);
            }
        }
    }


}
