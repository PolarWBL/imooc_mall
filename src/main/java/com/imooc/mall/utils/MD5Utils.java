package com.imooc.mall.utils;

import com.imooc.mall.common.Constant;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5 工具
 *
 * @author Boliang Weng
 */
public class MD5Utils {
    public static String getMd5String(String value) {
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + Constant.SALT);
        }
        String s = new String(chars);
        return DigestUtils.md5Hex(s);
    }
}
