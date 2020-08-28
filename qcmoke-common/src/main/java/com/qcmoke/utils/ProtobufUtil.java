package com.qcmoke.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;


/**
 * javaBean和protoBean相互转化工具类
 *
 * @author wenxiaohua
 * @date 2020/8/3 9:04
 */
public class ProtobufUtil {

    private static final Logger logger = LoggerFactory.getLogger(ProtobufUtil.class);
    private final static Gson gson = new GsonBuilder().serializeNulls().create();
    private static final JsonFormat.Printer defaultPrinter = JsonFormat.printer();
    private static final JsonFormat.Parser defaultParser = JsonFormat.parser();
    private final JsonFormat.Printer printer;
    private final JsonFormat.Parser parser;

    /**
     * 解决Any以及Oneof类型的数据
     */
    public ProtobufUtil(List<Descriptors.Descriptor> anyFieldDescriptor) {
        JsonFormat.TypeRegistry typeRegistry = JsonFormat.TypeRegistry.newBuilder().add(anyFieldDescriptor).build();
        this.printer = JsonFormat.printer().usingTypeRegistry(typeRegistry);
        this.parser = JsonFormat.parser().usingTypeRegistry(typeRegistry);
    }

    /**
     * proto对象转成javaBean对象(默认值的数据忽略)
     *
     * @param protoBean     proto对象
     * @param javaBeanClass javaBean Class对象
     * @param <T>           javaBean泛型
     * @return javaBean
     */
    public static <T> T toBean(Message protoBean, Class<T> javaBeanClass) {
        return toBean(protoBean, javaBeanClass, false);
    }

    /**
     * proto对象转成javaBean对象
     *
     * @param protoBean                     proto对象
     * @param javaBeanClass                 javaBean Class对象
     * @param isIncludingDefaultValueFields 是否保留proto对象属性的默认值（如果是false,protobuf buffer转换成json字符串时会把字段值是默认值的数据忽略）
     * @param <T>                           javaBean泛型
     * @return javaBean
     */
    public static <T> T toBean(Message protoBean, Class<T> javaBeanClass, boolean isIncludingDefaultValueFields) {
        return toBean(protoBean, javaBeanClass, defaultPrinter, isIncludingDefaultValueFields);
    }


    /**
     * @param protoBean          proto对象
     * @param javaBeanClass      javaBean Class对象
     * @param fieldDescriptorSet proto属性集合(可选性地保留proto对象属性的默认值，如果给空集则保留所有属性的默认值，否则按照集合的给值仅保留相关属性的默认值)
     * @param <T>                javaBean泛型
     * @return javaBean
     */
    public static <T> T toBean(Message protoBean, Class<T> javaBeanClass, Set<Descriptors.FieldDescriptor> fieldDescriptorSet) {
        return toBean(protoBean, javaBeanClass, defaultPrinter, fieldDescriptorSet);
    }


    /**
     * javaBean对象转成proto对象
     *
     * @param javaBean   javaBean
     * @param protoClass proto Class类型
     * @param <T>        proto泛型
     * @return proto对象
     */
    public static <T extends Message> T toProtoBean(Object javaBean, Class<T> protoClass) {
        return toProtoBean(javaBean, protoClass, defaultParser);
    }


    /**
     * proto对象转成javaBean对象(默认值的数据忽略)
     *
     * @param protoBean     proto对象
     * @param javaBeanClass javaBean Class对象
     * @param <T>           javaBean泛型
     * @return javaBean
     */
    public <T> T toBeanForSpecial(Message protoBean, Class<T> javaBeanClass) {
        return toBean(protoBean, javaBeanClass, this.printer, false);
    }

    /**
     * proto对象转成javaBean对象
     *
     * @param protoBean                     proto对象
     * @param javaBeanClass                 javaBean Class对象
     * @param isIncludingDefaultValueFields 是否保留proto对象属性的默认值（如果是false,protobuf buffer转换成json字符串时会把字段值是默认值的数据忽略）
     * @param <T>                           javaBean泛型
     * @return javaBean
     */
    public <T> T toBeanForSpecial(Message protoBean, Class<T> javaBeanClass, boolean isIncludingDefaultValueFields) {
        return toBean(protoBean, javaBeanClass, this.printer, isIncludingDefaultValueFields);
    }

    /**
     * proto对象转成javaBean对象
     *
     * @param protoBean          proto对象
     * @param javaBeanClass      javaBean Class对象
     * @param fieldDescriptorSet proto属性集合(可选性地保留proto对象属性的默认值，如果给空集则保留所有属性的默认值，否则按照集合的给值仅保留相关属性的默认值)
     * @param <T>                javaBean泛型
     * @return javaBean
     */
    public <T> T toBeanForSpecial(Message protoBean, Class<T> javaBeanClass, Set<Descriptors.FieldDescriptor> fieldDescriptorSet) {
        return toBean(protoBean, javaBeanClass, this.printer, fieldDescriptorSet);
    }


    public <T extends Message> T toProtoBeanForSpecial(Object javaBean, Class<T> protoClass) {
        return toProtoBean(javaBean, protoClass, this.parser);
    }

    private static <T> T toBean(
            Message protoBean,
            Class<T> javaBeanClass,
            JsonFormat.Printer printer,
            boolean isIncludingDefaultValueFields) {
        try {
            String jsonString;
            if (isIncludingDefaultValueFields) {
                jsonString = printer.includingDefaultValueFields().print(protoBean);
            } else {
                jsonString = printer.print(protoBean);
            }
            return gson.fromJson(jsonString, javaBeanClass);
        } catch (InvalidProtocolBufferException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    private static <T> T toBean(
            Message protoBean,
            Class<T> javaBeanClass,
            JsonFormat.Printer printer,
            Set<Descriptors.FieldDescriptor> fieldDescriptorSet) {
        try {
            String jsonString;
            if (fieldDescriptorSet == null || fieldDescriptorSet.size() == 0) {
                jsonString = printer.includingDefaultValueFields().print(protoBean);
            } else {
                jsonString = printer.includingDefaultValueFields(fieldDescriptorSet).print(protoBean);
            }
            return gson.fromJson(jsonString, javaBeanClass);
        } catch (InvalidProtocolBufferException e) {
            logger.error(e.getMessage());
            return null;
        }
    }


    @SuppressWarnings({"unchecked"})
    private static <T extends Message> T toProtoBean(Object javaBean, Class<T> protoClass, JsonFormat.Parser parser) {
        Message.Builder builder = null;
        try {
            builder = (Message.Builder) protoClass.getMethod("newBuilder").invoke(null);
            String json = gson.toJson(javaBean);
            //在json字符串转换成protobu的Ｍessage对象时，如果存在Message对象不存在的字段时会报InvalidProtocolBufferException，此时需要使用ignoringUnknownFields。
            parser.ignoringUnknownFields().merge(json, builder);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        return (T) builder.build();
    }
}