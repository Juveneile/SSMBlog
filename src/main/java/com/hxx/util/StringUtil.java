package com.hxx.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StringUtil
 * @Description //TODO
 * @Author haoxx
 * @Date 2019/4/4 - 16:38
 * @Version 1.0
 */
public class StringUtil {
    /**
     *@author haoxx
     *@Description //TODO  判断是否是空
     *@Date 2019/4/4 - 16:39
     *@Param [str]
     *@return boolean
     */
    public static boolean isEmpty(String str){
        if(str==null||"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }
    /**
     *@author haoxx
     *@Description //TODO 判断是否不是空
     *@Date 2019/4/4 - 16:40
     *@Param [str]
     *@return boolean
     */
    public static boolean isNotEmpty(String str){
        if((str!=null)&&!"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }
    /**
     *@author haoxx
     *@Description //TODO 格式化模糊查询
     *@Date 2019/4/4 - 16:40
     *@Param [str]
     *@return java.lang.String
     */
    public static String formatLike(String str){
        if(isNotEmpty(str)){
            return "%"+str+"%";
        }else{
            return null;
        }
    }
    /**
     *@author haoxx
     *@Description //TODO  过滤掉集合里的空格
     *@Date 2019/4/4 - 16:39
     *@Param [list]
     *@return java.util.List<java.lang.String>
     */
    public static List<String> filterWhite(List<String> list){
        List<String> resultList=new ArrayList<String>();
        for(String l:list){
            if(isNotEmpty(l)){
                resultList.add(l);
            }
        }
        return resultList;
    }
}
