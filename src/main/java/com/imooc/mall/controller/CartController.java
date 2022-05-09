package com.imooc.mall.controller;

import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.filter.UserFilter;
import com.imooc.mall.model.vo.CartVO;
import com.imooc.mall.service.CartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 购物车控制器
 *
 * @author Boliang Weng
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @ApiOperation("购物车列表")
    @GetMapping("/list")
    public ApiRestResponse<Object> list(){
        //内部获取用户id, 防止横向越权
        List<CartVO> cartVOList = cartService.list(UserFilter.currentUser.getId());
        return ApiRestResponse.success(cartVOList);
    }

    @ApiOperation("添加商品到购物车")
    @PostMapping("/add")
    public ApiRestResponse<Object> add(@RequestParam Integer productId, @RequestParam Integer count) {
        //内部获取用户id, 防止横向越权
        List<CartVO> cartVOList = cartService.add(UserFilter.currentUser.getId(), productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @ApiOperation("更新购物车")
    @PostMapping("/update")
    public ApiRestResponse<Object> update(@RequestParam Integer productId, @RequestParam Integer count) {
        //内部获取用户id, 防止横向越权
        List<CartVO> cartVOList = cartService.update(UserFilter.currentUser.getId(), productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @ApiOperation("删除购物车")
    @PostMapping("/delete")
    public ApiRestResponse<Object> delete(@RequestParam Integer productId) {
        //内部获取用户id, 防止横向越权
        //不能直接获取cartId, 否则可以删除别人的购物车
        List<CartVO> cartVOList = cartService.delete(UserFilter.currentUser.getId(), productId);
        return ApiRestResponse.success(cartVOList);
    }

    @ApiOperation("选中/不选中购物车")
    @PostMapping("/select")
    public ApiRestResponse<Object> select(@RequestParam Integer productId, @RequestParam Integer selected) {
        //内部获取用户id, 防止横向越权
        //不能直接获取cartId, 否则可以删除别人的购物车
        List<CartVO> cartVOList = cartService.selectOrNot(UserFilter.currentUser.getId(), productId, selected);
        return ApiRestResponse.success(cartVOList);
    }

    @ApiOperation("全选中/全不选中购物车")
    @PostMapping("/selectAll")
    public ApiRestResponse<Object> selectAll(@RequestParam Integer selected) {
        //内部获取用户id, 防止横向越权
        //不能直接获取cartId, 否则可以删除别人的购物车
        List<CartVO> cartVOList = cartService.selectAllOrNot(UserFilter.currentUser.getId(), selected);
        return ApiRestResponse.success(cartVOList);
    }
}











































