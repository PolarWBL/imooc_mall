package com.imooc.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.zxing.WriterException;
import com.imooc.mall.common.Constant;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.filter.UserFilter;
import com.imooc.mall.model.dao.CartMapper;
import com.imooc.mall.model.dao.OrderItemMapper;
import com.imooc.mall.model.dao.OrderMapper;
import com.imooc.mall.model.dao.ProductMapper;
import com.imooc.mall.model.pojo.Order;
import com.imooc.mall.model.pojo.OrderItem;
import com.imooc.mall.model.pojo.Product;
import com.imooc.mall.model.pojo.User;
import com.imooc.mall.model.request.CreateOrderReq;
import com.imooc.mall.model.vo.CartVO;
import com.imooc.mall.model.vo.OrderItemVO;
import com.imooc.mall.model.vo.OrderVO;
import com.imooc.mall.service.CartService;
import com.imooc.mall.service.OrderService;
import com.imooc.mall.service.UserService;
import com.imooc.mall.utils.OrderCodeFactory;
import com.imooc.mall.utils.QrCodeGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Boliang Weng
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private CartService cartService;

    @Resource
    private UserService userService;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Value("${file.upload.ip}")
    String ip;

    /**
     * Transactional开启数据库事务
     *
     * @param createOrderReq 创建订单
     * @return 返回订单流水号
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(CreateOrderReq createOrderReq) {

        //拿到用户ID
        Integer userId = UserFilter.currentUser.getId();
        //从用户的购物车中查找已勾选的商品
        List<CartVO> cartVOList = cartService.list(userId);
        List<CartVO> selectedCartVO = new ArrayList<>();
        for (CartVO cartVO : cartVOList) {
            if (Constant.CartStatus.SELECTED == cartVO.getSelected()) {
                selectedCartVO.add(cartVO);
            }
        }
        //若购物车中无已勾选的商品, 则抛出异常
        if (CollectionUtils.isEmpty(selectedCartVO)) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NO_SELECTED_CART);
        }
        //判断每一个商品是否存在, 以及是否上架, 库存是否足够, 否则抛出异常
        validProducts(selectedCartVO);
        //修改剩余库存
        updateStocks(selectedCartVO);
        //删除用户已勾选的购物车商品
        deleteSelectedCart(selectedCartVO);
        //将购物车列表转为订单详情列表
        List<OrderItem> orderItemList = cartsToOrderItems(selectedCartVO);
        //获取订单总价
        Integer totalPrice = orderTotalPrice(orderItemList);
        //创建订单
        Order order = new Order();
        //创建订单号
        String orderNo = OrderCodeFactory.getOrderCode(Long.valueOf(userId));
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        order.setReceiverName(createOrderReq.getReceiverName());
        order.setReceiverMobile(createOrderReq.getReceiverMobile());
        order.setReceiverAddress(createOrderReq.getReceiverAddress());
        order.setOrderStatus(Constant.OrderStatus.NOT_PAID.getCode());
        order.setPostage(Constant.POSTAGE);
        order.setPaymentType(Constant.PaymentType.PAY_ONLINE);
        //插入到 Order表
        orderMapper.insertSelective(order);
        //循环保存商品到order_item表
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderNo(orderNo);
            orderItemMapper.insertSelective(orderItem);

            //模拟产生错误, 测试事务
            //throw new ImoocMallException(ImoocMallExceptionEnum.SYSTEM_ERROR);
        }
        //返回订单号
        return orderNo;
    }

    @Override
    public OrderVO detail(String orderNo) {
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (order == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NO_ORDER);
        }
        //如果订单存在, 需要判断是否属于改用户
        Integer userId = UserFilter.currentUser.getId();
        if (!order.getUserId().equals(userId)) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NOT_YOUR_ORDER);
        }
        return getOrderVo(order);
    }

    @Override
    public PageInfo listForCustomer(Integer pageNum, Integer pageSize) {
        Integer userId = UserFilter.currentUser.getId();
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectByUserId(userId);
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Order order : orderList) {
            OrderVO orderVo = getOrderVo(order);
            orderVOList.add(orderVo);
        }
        PageInfo pageInfo = new PageInfo<>(orderList);
        pageInfo.setList(orderVOList);

        return pageInfo;
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.selectAll();
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Order order : orderList) {
            OrderVO orderVo = getOrderVo(order);
            orderVOList.add(orderVo);
        }
        PageInfo pageInfo = new PageInfo<>(orderList);
        pageInfo.setList(orderVOList);

        return pageInfo;
    }

    @Override
    public void cancelOrder(String orderNo) {
        //获取订单信息
        Order order = orderMapper.selectByOrderNo(orderNo);

        if (order == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NO_ORDER);
        }
        //如果订单存在, 需要判断是否属于该用户
        Integer userId = UserFilter.currentUser.getId();
        if (!order.getUserId().equals(userId)) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NOT_YOUR_ORDER);
        }

        if (order.getOrderStatus() != Constant.OrderStatus.NOT_PAID.getCode()) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NOT_ALLOWED_ACTION);
        }
        order.setOrderStatus(Constant.OrderStatus.CANCELED.getCode());
        order.setEndTime(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public String qrcode(String orderNo) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.SYSTEM_ERROR);
        }

        HttpServletRequest request = attributes.getRequest();

        String address = ip + ":" + request.getLocalPort();
        String payUrl = "http://" + address + "/pay?orderNo=" + orderNo;

        try {
            QrCodeGenerator.generateQrCodeImage(payUrl, 350, 350, Constant.FILE_UPLOAD_DIR + orderNo + ".png");
        } catch (WriterException | IOException e) {
            throw new ImoocMallException(ImoocMallExceptionEnum.SYSTEM_ERROR);
        }

        return "http://" + address + "/images/" + orderNo + ".png";
    }

    @Override
    public void pay(String orderNo) {
        //获取订单信息
        Order order = orderMapper.selectByOrderNo(orderNo);

        if (order == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NO_ORDER);
        }

        if (order.getOrderStatus() != Constant.OrderStatus.NOT_PAID.getCode()) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NOT_ALLOWED_ACTION);
        }

        order.setOrderStatus(Constant.OrderStatus.PAID.getCode());
        order.setPayTime(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void delivered(String orderNo) {
        //获取订单信息
        Order order = orderMapper.selectByOrderNo(orderNo);

        if (order == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NO_ORDER);
        }

        if (order.getOrderStatus() != Constant.OrderStatus.PAID.getCode()) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NOT_ALLOWED_ACTION);
        }

        order.setOrderStatus(Constant.OrderStatus.DELIVERED.getCode());
        order.setDeliveryTime(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void finish(String orderNo) {
        //获取订单信息
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (order == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NO_ORDER);
        }
        //判断用户类型
        User currentUser = UserFilter.currentUser;
        if (!userService.checkAdminRole(currentUser) && !order.getUserId().equals(currentUser.getId())) {
            //如果是普通用户, 且订单所属用户不是当前用户, 则不能操作
            throw new ImoocMallException(ImoocMallExceptionEnum.NOT_YOUR_ORDER);
        }

        if (order.getOrderStatus() != Constant.OrderStatus.DELIVERED.getCode()) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NOT_ALLOWED_ACTION);
        }

        order.setOrderStatus(Constant.OrderStatus.FINISHED.getCode());
        order.setEndTime(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
    }

    private OrderVO getOrderVo(Order order) {
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        List<OrderItem> orderItemList = orderItemMapper.selectByOrderNo(order.getOrderNo());
        List<OrderItemVO> orderItemVOList = new ArrayList<>();
        for (OrderItem orderItem : orderItemList) {
            OrderItemVO orderItemVO = new OrderItemVO();
            BeanUtils.copyProperties(orderItem, orderItemVO);

            orderItemVOList.add(orderItemVO);
        }
        orderVO.setOrderItemVOList(orderItemVOList);
        orderVO.setOrderStatusName(Constant.OrderStatus.codeOf(order.getOrderStatus()).getValue());

        return orderVO;
    }

    private Integer orderTotalPrice(List<OrderItem> orderItemList) {
        Integer totalPrice = 0;
        for (OrderItem orderItem : orderItemList) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

    private List<OrderItem> cartsToOrderItems(List<CartVO> cartVOList) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartVO cartVO : cartVOList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartVO.getProductId());
            //记录商品快照信息
            orderItem.setProductName(cartVO.getProductName());
            orderItem.setProductImg(cartVO.getProductImage());
            orderItem.setUnitPrice(cartVO.getPrice());
            orderItem.setQuantity(cartVO.getQuantity());
            orderItem.setTotalPrice(cartVO.getTotalPrice());
            //添加到列表
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    private void deleteSelectedCart(List<CartVO> cartVOList) {
        for (CartVO cartVO : cartVOList) {
            int count = cartMapper.deleteByPrimaryKey(cartVO.getId());
            if (count == 0) {
                throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
            }
        }
    }

    private void updateStocks(List<CartVO> cartVOList) {
        for (CartVO cartVO : cartVOList) {
            Product product = productMapper.selectByPrimaryKey(cartVO.getProductId());
            int stock = product.getStock() - cartVO.getQuantity();

            if (stock < 0) {
                throw new ImoocMallException(ImoocMallExceptionEnum.NOT_ENOUGH);
            }

            product.setStock(stock);
            int count = productMapper.updateByPrimaryKeySelective(product);

            if (count == 0) {
                throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
            }
        }
    }

    private void validProducts(List<CartVO> selectedCartVO) {
        for (CartVO cartVO : selectedCartVO) {
            cartService.validProduct(cartVO.getProductId(), cartVO.getQuantity());
        }
    }
}
