package org.classview.core.entity;

/**
 * @author gjt
 * @version 1.0
 * @date 2019/7/12 13:16
 * @Description flag常量
 */
public class AccFlag {
    /**
     * 是否为public类型
     */
    public static final String ACC_PUBLIC = "0x0001";
    /**
     * 是否被声明为final，只有类可设置
     */
    public static final String ACC_FINAL = "0x0010";
    /**
     * 是否允许使用invokespecial字节码指令，JDK1.2以后编译出来的类这个标志为真
     */
    public static final String ACC_SUPER = "0x0020";
    /**
     * 标识这是一个接口
     */
    public static final String ACC_INTERFACE = "0x0200";
    /**
     * 是否为abstract类型，对于接口和抽象类，此标志为真，其它类为假
     */
    public static final String ACC_ABSTRACT = "0x0400";
    /**
     * 标识别这个类并非由用户代码产生
     */
    public static final String ACC_SYNTHETIC = "0x1000";
    /**
     * 标识这是一个注解
     */
    public static final String ACC_ANNOTATION = "0x2000";
    /**
     * 标识这是一个枚举
     */
    public static final String ACC_ENUM = "0x4000";

}
