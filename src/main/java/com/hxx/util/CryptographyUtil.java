package com.hxx.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @ClassName CryptographyUtil
 * @Description //TODO 加密工具
 * @Author haoxx
 * @Date 2019/4/3 - 16:15
 * @Version 1.0
 */
public class CryptographyUtil {
    /**
     *@author haoxx
     *@Description //TODO  Md5加密
     *@Date 2019/4/3 - 16:15
     *@Param [str, salt]
     *@return java.lang.String
     */
    public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }

    public static void main(String[] args) {
        String password="123"; //3964769354199f1cdaaa68f7567a9d06
        System.out.println("Md5加密："+CryptographyUtil.md5(password, "hxx"));
    }
}
