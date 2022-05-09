package com.imooc.mall.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 添加目录的请求类
 *
 * @author Boliang Weng
 */
public class AddCategoryReq {
    @NotNull(message = "目录名称不能为空")
    @Size(min = 2, max = 5, message = "目录名称必须在2-5个字符之间")
    private String name;

    @NotNull(message = "目录层级不能为空")
    @Max(value = 3, message = "目录层级不能大于3")
    private Integer type;

    @NotNull(message = "父目录的ID不能为空")
    private Integer parentId;

    @NotNull(message = "排序值不能为空")
    private Integer orderNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "AddCategoryReq{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", parentId=" + parentId +
                ", orderNum=" + orderNum +
                '}';
    }
}
