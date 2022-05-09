package com.imooc.mall.controller;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.model.pojo.Product;
import com.imooc.mall.model.request.ListProductReq;
import com.imooc.mall.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 前台商品相关
 * @author Boliang Weng
 */
@RestController
public class ProductController {
    @Resource
    private ProductService productService;

    @ApiOperation("商品详情")
    @GetMapping("/product/detail")
    public ApiRestResponse<Object> detail(@RequestParam Integer id) {
        Product detail = productService.detail(id);
        return ApiRestResponse.success(detail);
    }

    @ApiOperation("商品列表")
    @GetMapping("/product/list")
    public ApiRestResponse<Object> list(ListProductReq listProductReq) {

        PageInfo<Product> pageInfo = productService.listProductForCustomer(listProductReq);
        return ApiRestResponse.success(pageInfo);
    }


}
