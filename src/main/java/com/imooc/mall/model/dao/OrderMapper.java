package com.imooc.mall.model.dao;

import com.imooc.mall.model.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Boliang Weng
 */
@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectByOrderNo(@Param("orderNo") String orderNo);

    List<Order> selectByUserId(@Param("userId") Integer userId);

    List<Order> selectAll();
}