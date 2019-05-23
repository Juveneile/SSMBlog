package com.hxx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description //TODO 日期工具类
 * @Author haoxx
 * @Date 2019/4/7 - 20:53
 * @Version 1.0
 */
public class DateUtil {
    /**
     *@author haoxx
     *@Description //TODO 日期对象转字符串
     *@Date 2019/4/7 - 20:53
     *@Param [date, format]
     *@return java.lang.String
     */
    public static String formatDate(Date date, String format){
        String result="";
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        if(date!=null){
            result=sdf.format(date);
        }
        return result;
    }

    /**
     *@author haoxx
     *@Description //TODO 字符串转日期对象
     *@Date 2019/4/7 - 20:53
     *@Param [str, format]
     *@return java.util.Date
     */
    public static Date formatString(String str,String format) throws Exception{
        if(StringUtil.isEmpty(str)){
            return null;
        }
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        return sdf.parse(str);
    }

    public static String getCurrentDateStr()throws Exception{
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }
}
