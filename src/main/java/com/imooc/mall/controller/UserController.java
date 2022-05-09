package com.imooc.mall.controller;

import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.common.Constant;
import com.imooc.mall.exception.ImoocMallException;
import com.imooc.mall.exception.ImoocMallExceptionEnum;
import com.imooc.mall.model.pojo.User;
import com.imooc.mall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 *
 * 用户控制器
 * @author Boliang Weng
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/test")
    @ResponseBody
    public User personalPage(){
        return userService.getUser();
    }

    @PostMapping("/register")
    @ResponseBody
    public ApiRestResponse<Object> register(@RequestParam("userName") String useName, @RequestParam("password") String password) {
        if (StringUtils.isEmpty(useName)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        }
        //密码长度不能小于8位
        if (password.length() < 8) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.PASSWORD_TOO_SHORT);
        }
        userService.register(useName, password);
        return ApiRestResponse.success();
    }

    @PostMapping("/login")
    @ResponseBody
    public ApiRestResponse<User> login(@RequestParam("userName") String useName,
                                         @RequestParam("password") String password,
                                         HttpSession session) {
        if (StringUtils.isEmpty(useName)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(useName, password);
        //保存用户信息时, 删除密码
        user.setPassword(null);
        session.setAttribute(Constant.IMOOC_MALL_USER, user);
        return ApiRestResponse.success(user);
    }

    @PostMapping("/user/update")
    @ResponseBody
    public ApiRestResponse<Object> updateUserInfo(@RequestParam("signature") String signature,
                                                  HttpSession session) {
        User currentUser = (User) session.getAttribute(Constant.IMOOC_MALL_USER);
        if (currentUser == null) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_LOGIN);
        }
        User user = new User();
        user.setId(currentUser.getId());
        user.setPersonalizedSignature(signature);
        userService.updateInfo(user);
        return ApiRestResponse.success();
    }

    @PostMapping("/user/logout")
    @ResponseBody
    public ApiRestResponse<Object> logout(HttpSession session){
        session.removeAttribute(Constant.IMOOC_MALL_USER);
        return ApiRestResponse.success();
    }

    @PostMapping("/adminLogin")
    @ResponseBody
    public ApiRestResponse<User> adminLogin(@RequestParam("userName") String useName,
                                       @RequestParam("password") String password,
                                       HttpSession session) {
        if (StringUtils.isEmpty(useName)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(useName, password);
        //校验是否为管理员
        if (userService.checkAdminRole(user)) {
            //保存用户信息时, 删除密码
            user.setPassword(null);
            session.setAttribute(Constant.IMOOC_MALL_USER, user);
            return ApiRestResponse.success(user);
        }else {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NOT_ADMIN);
        }

    }

}
