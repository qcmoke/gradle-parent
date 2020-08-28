package com.qcmoke.utils;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther wenxiaohua
 * @date 2020/8/21 12:13
 */
public class DescriptorUtil {
    private static final Logger logger = LoggerFactory.getLogger(DescriptorUtil.class);

    /**
     * 通过lambda引用get或者is方法的获取proto对象的属性集合
     *
     * @param protoClass    proto类
     * @param typeFunctions get或者is方法集合，eg:  QueryAccessDetailResponse::getNote
     * @param <T>           T
     * @param <R>           R
     * @return 取proto对象的属性集合
     */
    @SafeVarargs
    public static <T, R, Z extends Message> Set<Descriptors.FieldDescriptor> getFieldDescriptorSet(Class<Z> protoClass,TypeFunction<T, R>... typeFunctions) {
        Set<Descriptors.FieldDescriptor> fieldDescriptorHashSet = new HashSet<>();
        if (protoClass == null || typeFunctions == null || typeFunctions.length == 0) {
            return fieldDescriptorHashSet;
        }
        try {
            Message.Builder builder = (Message.Builder) protoClass.getMethod("newBuilder").invoke(null);
            for (TypeFunction<T, R> typeFunction : typeFunctions) {
                String name = LambdaUtil.getJavaBeanAttributeName(typeFunction);
                Descriptors.FieldDescriptor field = builder.getDescriptorForType().findFieldByName(name);
                fieldDescriptorHashSet.add(field);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return fieldDescriptorHashSet;
    }
}
