package com.jesgoo.fast.utils;

/**
 * @author:wangkang Date:2016/8/22
 * Time:20:44
 * Des:String帮助类
 */
public class StringUtils {

    /**
     * 检查字符串是否为空
     */
    public static boolean isStringEmpty(String param) {
        if (null != param && !"".equals(param)) {
            return false;
        } else {
            return true;
        }
    }


}
