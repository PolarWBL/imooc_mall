package com.imooc.mall.controller;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.model.pojo.Product;
import com.imooc.mall.model.request.CreateOrderReq;
import com.imooc.mall.model.vo.OrderVO;
import com.imooc.mall.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单控制器
 *
 * @author Boliang Weng
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @ApiOperation("创建订单")
    @PostMapping("/order/create")
    public ApiRestResponse<String> create(@RequestBody CreateOrderReq createOrderReq) {
        String orderNo = orderService.createOrder(createOrderReq);
        return ApiRestResponse.success(orderNo);
    }

    @ApiOperation("前台: 获取订单详情")
    @GetMapping("/order/detail")
    public ApiRestResponse<Object> detail(@RequestParam("orderNo") String orderNo) {
        OrderVO orderVO = orderService.detail(orderNo);
        return ApiRestResponse.success(orderVO);
    }

    @ApiOperation("前台: 获取订单列表")
    @GetMapping("/order/list")
    public ApiRestResponse<Object> listForCustomer(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo pageInfo = orderService.listForCustomer(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("前台: 取消订单")
    @PostMapping("/order/cancel")
    public ApiRestResponse<Object> cancelOrder(@RequestParam("orderNo") String orderNo) {
        orderService.cancelOrder(orderNo);
        return ApiRestResponse.success();
    }


    @ApiOperation("前台: 生成二维码")
    @GetMapping("/order/qrcode")
    public ApiRestResponse<Object> qrcode(@RequestParam("orderNo") String orderNo) {
        String imageAddress = orderService.qrcode(orderNo);
        return ApiRestResponse.success(imageAddress);
    }


    @ApiOperation("前台: 支付订单")
    @GetMapping("/pay")
    public ApiRestResponse<Object> pay(@RequestParam("orderNo") String orderNo) {
        orderService.pay(orderNo);
        return ApiRestResponse.success();
    }

    @ApiOperation("订单完结(前后台通用)")
    @PostMapping("/order/finish")
    public ApiRestResponse<Object> finish(@RequestParam("orderNo") String orderNo) {
        orderService.finish(orderNo);
        return ApiRestResponse.success();
    }

}
