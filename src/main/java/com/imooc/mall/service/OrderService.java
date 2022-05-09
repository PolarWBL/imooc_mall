package com.imooc.mall.service;

import com.github.pagehelper.PageInfo;
import com.imooc.mall.model.request.CreateOrderReq;
import com.imooc.mall.model.vo.OrderVO;

import java.util.List;

/**
 * @author Boliang Weng
 */
public interface OrderService {

    /**
     *  创建订单
     * @param createOrderReq 创建订单
     * @return 返回订单流水号
     */
    String createOrder(CreateOrderReq createOrderReq);

    /**
     *  前台获取订单详情
     * @param orderNo 订单流水号
     * @return 返回
     */
    OrderVO detail(String orderNo);

    /**
     *  前台获取订单列表
     * @param pageNum 分页页码
     * @param pageSize 分页大小
     * @return 订单列表
     */
    PageInfo listForCustomer(Integer pageNum, Integer pageSize);


    /**
     *  后台管理员 获取订单列表
     * @param pageNum 分页页码
     * @param pageSize 分页大小
     * @return 订单列表
     */
    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

    /**
     *  用户取消订单
     * @param orderNo 订单流水号
     */
    void cancelOrder(String orderNo);

    /**
     *  生成支付二维码
     * @param orderNo 订单流水号
     * @return 二维码图片链接
     */
    String qrcode(String orderNo);


    /**
     *  订单支付接口
     * @param orderNo 订单流水号
     */
    void pay(String orderNo);

    /**
     *  管理员发货
     * @param orderNo 要发货的订单流水号
     */
    void delivered(String orderNo);

    /**
     *  订单完成(前后台通用)
     * @param orderNo 要完成的订单流水号
     */
    void finish(String orderNo);
}
