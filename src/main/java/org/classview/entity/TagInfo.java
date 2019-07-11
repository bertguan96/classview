package org.classview.entity;

/**
 * @author gjt
 * @version 1.0
 * @date 2019/7/11 22:53
 * @Description 常量池标志
 */
public class TagInfo {
    /**
     * UTF-8编码的Unicode字符串
     */
    public final static int CONSTANT_UTF8 = 1;
    /**
     * int类型的字面值
     */
    public final static int CONSTANT_INTEGER = 3;
    /**
     * float类型的字面值
     */
    public final static int CONSTANT_FLOAT = 4;
    /**
     * long类型的字面值
     */
    public final static int CONSTANT_LONG = 5;
    /**
     * 	double类型的字面值
     */
    public final static int CONSTANT_DOUBLE = 6;
    /**
     * 类类型常量
     */
    public final static int CONSTANT_CLASS = 7;
    /**
     * 字符串常量
     */
    public final static int CONSTANT_STRING = 8;
    /**
     * 对一个字段的符号引用
     */
    public final static int CONSTANT_FIELD_REF = 9;
    /**
     * 对一个类中方法的符号引用
     */
    public final static int CONSTANT_METHOD_REF = 10;
    /**
     * 	对一个接口中方法的符号引用
     */
    public final static int CONSTANT_INTERFACE_METHOD_REF = 11;
    /**
     * 对一个字段或方法的部分符号引用
     */
    public final static int CONSTANT_NAME_AND_TYPE = 12;

}
