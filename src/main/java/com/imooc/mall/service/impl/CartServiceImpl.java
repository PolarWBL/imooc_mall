package com.imooc.mall.service.impl;

import com.imooc.mall.common.Constant;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.dao.CartMapper;
import com.imooc.mall.model.dao.ProductMapper;
import com.imooc.mall.model.pojo.Cart;
import com.imooc.mall.model.pojo.Product;
import com.imooc.mall.model.vo.CartVO;
import com.imooc.mall.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Boliang Weng
 */
@Service("cartService")
public class CartServiceImpl implements CartService {
    @Resource
    private CartMapper cartMapper;
    @Resource
    private ProductMapper productMapper;


    @Override
    public List<CartVO> list(Integer userId) {
        List<CartVO> cartVOList = cartMapper.selectList(userId);
        for (CartVO cartVO : cartVOList) {
            cartVO.setTotalPrice(cartVO.getPrice() * cartVO.getQuantity());
        }
        return cartVOList;
    }

    @Override
    public List<CartVO> add(Integer userId, Integer productId, Integer count) {
        validProduct(productId, count);

        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart == null) {
            //1. 商品不在购物车中, 新建购物车
            cart = new Cart();
            cart.setProductId(productId);
            cart.setUserId(userId);
            cart.setQuantity(count);
            cart.setSelected(Constant.CartStatus.SELECTED);

            cartMapper.insertSelective(cart);
        }else {
            //2. 商品已经在购物车中, 增加数量
            Integer quantity = cart.getQuantity() + count;
            Cart cartNew = new Cart();
            cartNew.setId(cart.getId());
            cartNew.setUserId(cart.getUserId());
            cartNew.setProductId(cart.getProductId());
            cartNew.setQuantity(quantity);

            cartMapper.updateByPrimaryKeySelective(cartNew);
        }

        return this.list(userId);
    }

    @Override
    public List<CartVO> update(Integer userId, Integer productId, Integer count) {
        validProduct(productId, count);

        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart == null) {
            //1. 商品不在购物车中, 更新失败
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }else {
            //2. 商品已经在购物车中, 更新数量
            Cart cartNew = new Cart();
            cartNew.setId(cart.getId());
            cartNew.setUserId(cart.getUserId());
            cartNew.setProductId(cart.getProductId());

            cartNew.setQuantity(count);

            cartMapper.updateByPrimaryKeySelective(cartNew);
        }

        return this.list(userId);
    }

    @Override
    public List<CartVO> delete(Integer userId, Integer productId) {
        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart == null) {
            //1. 商品不在购物车中, 删除失败
            throw new ImoocMallException(ImoocMallExceptionEnum.DELETE_FAILED);
        }else {
            //2. 商品已经在购物车中, 删除
            cartMapper.deleteByPrimaryKey(cart.getId());
        }
        return this.list(userId);
    }

    @Override
    public List<CartVO> selectOrNot(Integer userId, Integer productId, Integer selected) {
        //判断selected参数是否正确
        if (selected == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }else if (Constant.CartStatus.UN_SELECTED != selected && Constant.CartStatus.SELECTED != selected){
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }
        Cart cart = cartMapper.selectByUserIdAndProductId(userId, productId);
        if (cart == null) {
            //1. 商品不在购物车中, 选中状态更新失败
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }else {
            //2. 商品已经在购物车中, 更新状态
            int count = cartMapper.updateSelected(userId, productId, selected);
            if (count == 0) {
                throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
            }
        }
        return this.list(userId);
    }

    @Override
    public List<CartVO> selectAllOrNot(Integer userId, Integer selected) {
        //判断selected参数是否正确
        if (selected == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }else if (Constant.CartStatus.UN_SELECTED != selected && Constant.CartStatus.SELECTED != selected){
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }
        //改变选中状态
        int count = cartMapper.updateSelected(userId, null, selected);

        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }
        return this.list(userId);
    }

    @Override
    public void validProduct(Integer productId, Integer count) {
        Product product = productMapper.selectByPrimaryKey(productId);
        //判断商品是否存在, 是否上架
        if (product == null || product.getStatus().equals(Constant.SaleStatus.NOT_SALE)) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NOT_SALE);
        }
        //判断商品库存
        if (count > product.getStock()) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NOT_ENOUGH);
        }
    }


}
