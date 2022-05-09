package com.imooc.mall.controller;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 后台管理 订单 控制器
 * @author Boliang Weng
 */
@RestController
public class OrderAdminController {
    @Resource
    private OrderService orderService;

    @ApiOperation("后台: 获取订单列表")
    @GetMapping("/admin/order/list")
    public ApiRestResponse<Object> listForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo pageInfo = orderService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("后台: 订单发货")
    @PostMapping("/admin/order/delivered")
    public ApiRestResponse<Object> delivered(@RequestParam("orderNo") String orderNo) {
        orderService.delivered(orderNo);
        return ApiRestResponse.success();
    }

}
