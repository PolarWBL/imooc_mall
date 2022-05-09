package com.imooc.mall.controller;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.model.pojo.Category;
import com.imooc.mall.model.request.AddCategoryReq;
import com.imooc.mall.model.request.UpdateCategoryReq;
import com.imooc.mall.model.vo.CategoryVO;
import com.imooc.mall.service.CategoryService;
import com.imooc.mall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 商品分类 controller
 *
 * @author Boliang Weng
 */
@Controller
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @ApiOperation("后台-新增目录")
    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiRestResponse<Object> addCategory(@Valid @RequestBody AddCategoryReq addCategoryReq) {
        //添加商品分类
        categoryService.add(addCategoryReq);
        return ApiRestResponse.success();

    }

    @ApiOperation("后台-更新目录")
    @PostMapping("/admin/category/update")
    @ResponseBody
    public ApiRestResponse<Object> updateCategory(@Valid @RequestBody UpdateCategoryReq updateCategoryReq) {
        //更新商品分类
        categoryService.update(updateCategoryReq);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台-删除目录")
    @PostMapping("/admin/category/delete")
    @ResponseBody
    public ApiRestResponse<Object> deleteCategory(@RequestParam Integer id) {
        //删除商品分类
        categoryService.delete(id);
        return ApiRestResponse.success();
    }


    @ApiOperation("后台-获取分类列表(平铺)")
    @GetMapping("/admin/category/list")
    @ResponseBody
    public ApiRestResponse<Object> listCategoryForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        //分类列表（平铺）
        PageInfo<Category> pageInfo = categoryService.listCategoryForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("前台-获取分类列表(递归)")
    @GetMapping("/category/list")
    @ResponseBody
    public ApiRestResponse<Object> listCategoryForCustomer() {
        //分类列表（递归）
        List<CategoryVO> categoryVOList = categoryService.listCategoryForCustomer(0);
        return ApiRestResponse.success(categoryVOList);
    }

}
