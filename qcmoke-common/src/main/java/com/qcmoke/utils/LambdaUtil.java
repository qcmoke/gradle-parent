package com.qcmoke.utils;

/**
 * Lambda工具类
 *
 * @auther wenxiaohua
 * @date 2020/8/21 7:14
 */
public class LambdaUtil {

    /**
     * 通过lambda引用get或者is方法的获取javaBean属性名称
     *
     * @param typeFunction get或者is方法，eg:  QueryAccessDetailResponse::getNote
     * @param <T>          T
     * @param <R>          R
     * @return javaBean属性名称
     */
    public static <T, R> String getJavaBeanAttributeName(TypeFunction<T, R> typeFunction) {
        return TypeFunction.getLambdaColumnName(typeFunction);
    }
}
