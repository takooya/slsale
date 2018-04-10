package org.slsale.common;

/**
 * @Author takooya
 * @Description 防止sql注入的工具类
 * @Date:created in 21:16 2018/4/10
 */
public class SQLTools {
    /**  mybatis模糊查询时防止sql注入 (即做一个字符替换) */
    public static String transfer(String keyword){
        if(keyword.contains("%")||keyword.contains("_")){
            keyword=keyword.replaceAll("\\\\","\\\\\\\\")
                    .replaceAll("\\%","\\\\%")
                    .replaceAll("\\_","\\\\_");
        }
        return keyword;
    }
}
