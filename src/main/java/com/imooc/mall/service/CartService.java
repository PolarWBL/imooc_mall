package com.imooc.mall.service;

import com.imooc.mall.model.vo.CartVO;

import java.util.List;

/**
 * 购物车的服务类
 *
 * @author Boliang Weng
 */
public interface CartService {

    /**
     *  获取用户购物车列表
     * @param userId 用户id
     * @return 购物车列表
     */
    List<CartVO> list(Integer userId);

    /**
     *  添加购物车
     * @param userId 用户id
     * @param productId 商品id
     * @param count 商品数量
     * @return 购物车列表
     */
    List<CartVO> add(Integer userId, Integer productId, Integer count);

    /**
     *  更新购物车
     * @param userId 用户id
     * @param productId 商品id
     * @param count 商品数量
     * @return 购物车列表
     */
    List<CartVO> update(Integer userId, Integer productId, Integer count);

    /**
     *  删除购物车
     * @param userId 用户id
     * @param productId 商品id
     * @return 购物车列表
     */
    List<CartVO> delete(Integer userId, Integer productId);

    /**
     *  (不)选中购物车
     * @param userId 用户id
     * @param productId 商品id
     * @param selected 是否选中 1-选中 2-不选中
     * @return 购物车列表
     */
    List<CartVO> selectOrNot(Integer userId, Integer productId, Integer selected);

    /**
     *  全(不)选中购物车
     * @param userId 用户id
     * @param selected 是否选中 1-选中 2-不选中
     * @return 购物车列表
     */
    List<CartVO> selectAllOrNot(Integer userId, Integer selected);

    /**
     *  判断商品是否存在, 是否已上架, 是否有足够的库存, 否则抛出异常
     * @param productId  商品id
     * @param count 商品数量
     */
    void validProduct(Integer productId, Integer count);
}
