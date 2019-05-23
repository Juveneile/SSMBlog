package com.hxx.controller.admin;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;

/**
 * @ClassName DateJsonValueProcessor
 * @Description //TODO json-lib 日期处理类
 * @Author haoxx
 * @Date 2019/4/8 - 12:24
 * @Version 1.0
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

    private String format;

    public DateJsonValueProcessor(String format){
        this.format = format;
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        // TODO Auto-generated method stub
        return null;
    }

    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if(value == null)
        {
            return "";
        }
        if(value instanceof java.sql.Timestamp)
        {
            String str = new SimpleDateFormat(format).format((java.sql.Timestamp)value);
            return str;
        }
        if (value instanceof java.util.Date)
        {
            String str = new SimpleDateFormat(format).format((java.util.Date) value);
            return str;
        }

        return value.toString();
    }

}
