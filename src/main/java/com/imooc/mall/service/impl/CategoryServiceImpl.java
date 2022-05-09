package com.imooc.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.dao.CategoryMapper;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.request.AddCategoryReq;
import com.imooc.mall.model.request.UpdateCategoryReq;
import com.imooc.mall.model.vo.CategoryVO;
import com.imooc.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Boliang Weng
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public void add(AddCategoryReq addCategoryReq){
        Category category = new Category();
        BeanUtils.copyProperties(addCategoryReq, category);
        Category categoryOld = categoryMapper.selectByName(category.getName());

        if (categoryOld != null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }
        int count = categoryMapper.insertSelective(category);
        if (count != 1) {
            throw new ImoocMallException(ImoocMallExceptionEnum.CREATE_FAILED);
        }
    }


    @Override
    public void update(UpdateCategoryReq updateCategoryReq){

        Category category = new Category();
        BeanUtils.copyProperties(updateCategoryReq, category);

        if (category.getName() != null) {
            Category categoryOld = categoryMapper.selectByName(category.getName());
            if (categoryOld != null && !categoryOld.getId().equals(category.getId())) {
                throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
            }
        }
        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if (count != 1) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer id) {
        Category categoryOld = categoryMapper.selectByPrimaryKey(id);
        //查不到记录, 删除失败
        if (categoryOld == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.DELETE_FAILED);
        }
        int count = categoryMapper.deleteByPrimaryKey(id);
        if (count != 1) {
            throw new ImoocMallException(ImoocMallExceptionEnum.DELETE_FAILED);
        }

    }

    @Override
    public PageInfo<Category> listCategoryForAdmin(Integer pageNum, Integer pageSize) {
        //分类列表（平铺）
        PageHelper.startPage(pageNum, pageSize, "type, order_num");
        List<Category> categoryList = categoryMapper.selectList();
        return new PageInfo<>(categoryList);
    }

    /**
     * 使用redis 缓存
     */
    @Override
    @Cacheable(value = "listCategoryForCustomer")
    public List<CategoryVO> listCategoryForCustomer(Integer parentId) {
        //分类列表（平铺）
        List<CategoryVO> categoryVOList = new ArrayList<>();
        recursivelyFindCategories(categoryVOList, parentId);
        return categoryVOList;
    }

    private void recursivelyFindCategories(List<CategoryVO> categoryVOList, Integer parentId) {
        //递归获取所有子类别, 并组合成为一个"目录树"
        List<Category> categoryList = categoryMapper.selectListByParentId(parentId);
        if (!CollectionUtils.isEmpty(categoryList)) {
            for (Category category : categoryList) {
                CategoryVO categoryVO = new CategoryVO();
                BeanUtils.copyProperties(category, categoryVO);
                categoryVOList.add(categoryVO);
                recursivelyFindCategories(categoryVO.getChildCategory(), categoryVO.getId());
            }
        }
    }




}
