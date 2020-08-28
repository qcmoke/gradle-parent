package com.qcmoke.utils;

import java.beans.Introspector;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Function;

/**
 * @auther wenxiaohua
 * @date 2020/8/21 12:13
 */
@FunctionalInterface
public interface TypeFunction<T, R> extends Serializable, Function<T, R> {

    /**
     * 获取类属性名称
     *
     * @param lambda Serializable
     * @return String 类属性名称
     */
    static String getLambdaColumnName(Serializable lambda) {
        try {
            Method method = lambda.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda serializedLambda = (SerializedLambda) method.invoke(lambda);
            String methodName = serializedLambda.getImplMethodName();
            String attributeName = null;
            if (methodName.startsWith("is")) {
                attributeName = methodName.replace("is", "");
            }
            if (methodName.startsWith("get")) {
                attributeName = methodName.replace("get", "");
            }
            if (attributeName == null) {
                throw new RuntimeException(methodName + "是不可反序列化的方法!仅支持get和is开头的方法");
            }
            return Introspector.decapitalize(attributeName);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }
}

