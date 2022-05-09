package com.imooc.mall.service.impl;

import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.dao.UserMapper;
import com.imooc.mall.model.pojo.User;
import com.imooc.mall.service.UserService;
import com.imooc.mall.utils.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Boliang Weng
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }

    @Override
    public void register(String userName, String password) throws ImoocMallException {
        //查询用户名是否存在
        User result = userMapper.selectByName(userName);
        if (result != null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }
        //将用户信息写入数据库
        User user = new User();
        user.setUsername(userName);
        user.setPassword(MD5Utils.getMd5String(password));
        int count = userMapper.insertSelective(user);
        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.INSERT_FAILED);
        }

    }

    @Override
    public User login(String userName, String password) throws ImoocMallException {
        //获取md5形式密码
        String md5Password = MD5Utils.getMd5String(password);
        User user = userMapper.selectLogin(userName, md5Password);
        if (user == null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.WRONG_PASSWORD);
        }
        return user;
    }

    @Override
    public void updateInfo(User user) throws ImoocMallException {
        //更新个性签名
        int count = userMapper.updateByPrimaryKeySelective(user);
        if (count != 1) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public boolean checkAdminRole(User user) {
        //1为普通用户, 2为管理员用户
        return user.getRole().equals(2);
    }
}
