package com.imooc.mall.service;

import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.model.pojo.User;

/**
 * @author Boliang Weng
 */
public interface UserService {
    /**
     *  获取用户信息
     * @return 返回用户
     */
    User getUser();

    /**
     *  用户注册
     *
     * @param userName 用户名
     * @param password 密码
     */
    void register(String userName, String password) throws ImoocMallException;


    /**
     * 用户登录校验
     * @param userName 用户名
     * @param password 密码
     * @return 返回用户
     * @throws ImoocMallException 用户名或密码错误
     */
    User login(String userName, String password) throws ImoocMallException;

    /**
     *  更新个性签名
     * @param user 用户
     * @throws ImoocMallException 更新失败
     */
    void updateInfo(User user) throws ImoocMallException;

    /**
     *  判断是否为管理员用户
     * @param user 用户
     * @return 是否为管理员
     */
    boolean checkAdminRole(User user);
}
