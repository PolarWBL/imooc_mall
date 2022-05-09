package com.imooc.mall.exception;

/**
 * 异常枚举类
 *
 * @author Boliang Weng
 */
public enum ImoocMallExceptionEnum {
    /**
     * 错误信息码
     */
    NEED_USER_NAME(10001, "用户名不能为空"),
    NEED_PASSWORD(10002, "密码不能为空"),
    PASSWORD_TOO_SHORT(10003, "密码长度不能小于8位"),
    NAME_EXISTED(10004, "名称已存在"),
    INSERT_FAILED(10005, "用户注册失败"),
    WRONG_PASSWORD(10006, "密码错误"),
    NEED_LOGIN(10007, "请登录后再试"),
    UPDATE_FAILED(10008, "更新失败"),
    NOT_ADMIN(10009, "无管理员权限"),
    PARAM_NOT_NULL(10010, "内容不能为空"),
    CREATE_FAILED(10011, "新增失败"),
    REQUEST_PARAM_ERROR(10012, "参数错误"),
    DELETE_FAILED(10013, "删除失败"),
    MKDIR_FAILED(10014, "文件夹创建失败"),
    UPLOAD_FAILED(10015, "文件上传失败"),
    NO_DETAIL(10016, "没有此商品的信息"),
    NOT_SALE(10017, "此商品未上架"),
    NOT_ENOUGH(10018, "商品库存不足"),
    NO_SELECTED_CART(10019, "请选择要下单的商品"),
    NO_ENUM(10020, "未找到该状态"),
    NO_ORDER(10021, "订单不存在"),
    NOT_YOUR_ORDER(10022, "您没有此订单"),
    NOT_ALLOWED_ACTION(10023, "不允许此操作"),





    SYSTEM_ERROR(20000, "系统异常");





    /**
     * 异常码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String msg;

    ImoocMallExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
