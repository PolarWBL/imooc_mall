package com.imooc.mall.common;

import com.google.common.collect.Sets;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 常量值
 *
 * @author Boliang Weng
 */
@Component
public class Constant {
    public static final Integer SALT = 10050099;
    public static final String IMOOC_MALL_USER = "imooc_mall_user";
    public static final Integer POSTAGE = 0;
    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }

    public interface ProductListOrderBy{
        Set<String> PRICE_ASE_DESC = Sets.newHashSet("price desc", "price asc");
    }

    public interface SaleStatus{
        //商品处于下架状态
        int NOT_SALE = 0;
        //商品处于上架状态
        int SALE = 1;
    }

    public interface CartStatus{
        // 单个商品购物车未选中状态
        int UN_SELECTED = 0;
        // 单个商品购物车选中状态
        int SELECTED = 1;
    }

    public interface PaymentType{
        // 支付方式
        int PAY_ONLINE = 1;
    }

    public enum OrderStatus {
        /**
         * 订单状态: 0用户已取消，10未付款（初始状态），20已付款，30已发货，40交易完成
         */
        CANCELED(0,"用户已取消"),
        NOT_PAID(10,"未付款"),
        PAID(20,"已付款"),
        DELIVERED(30,"已发货"),
        FINISHED(40,"交易完成");

        private int code;
        private String value;

        public static OrderStatus codeOf(int code) {
            for (OrderStatus status : values()) {
                if (status.getCode() == code) {
                    return status;
                }
            }
            throw new ImoocMallException(ImoocMallExceptionEnum.NO_ENUM);
        }


        OrderStatus(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


}
