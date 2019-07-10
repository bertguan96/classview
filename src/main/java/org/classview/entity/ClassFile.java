package org.classview.entity;

/**
 * @author gjt
 * @version 1.0
 * @date 2019/7/10 18:15
 * @Description 这里写描述内容
 */
public class ClassFile {

    /**
     * 魔数
     */
    private String magic;
    /**
     * 小版本
     */
    private String minorVersion;
    /**
     * 大版本
     */
    private String majorVersion;
    /**
     * 常量池
     */
    private ConstantPool constantPool;
    /**
     *
     */
    private String accessFlags;
    /**
     *
     */
    private String thisClass;
    /**
     *
     */
    private String superClass;
    /**
     *
     */
    private String interfaceClass;
    /**
     *
     */
    private MemberInfo[] fields;
    /**
     *
     */
    private MemberInfo[] methods;
    /**
     *
     */
    private AttributeInfo[] attributes;

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    public String getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(String minorVersion) {
        this.minorVersion = minorVersion;
    }

    public String getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(String majorVersion) {
        this.majorVersion = majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public String getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(String accessFlags) {
        this.accessFlags = accessFlags;
    }

    public String getThisClass() {
        return thisClass;
    }

    public void setThisClass(String thisClass) {
        this.thisClass = thisClass;
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public String getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(String interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public MemberInfo[] getFields() {
        return fields;
    }

    public void setFields(MemberInfo[] fields) {
        this.fields = fields;
    }

    public MemberInfo[] getMethods() {
        return methods;
    }

    public void setMethods(MemberInfo[] methods) {
        this.methods = methods;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }
}
