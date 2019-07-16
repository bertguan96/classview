package org.classview.core.entity;

import javafx.scene.control.Menu;
import javafx.scene.control.TreeItem;

import java.util.Arrays;
import java.util.List;

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
    private List<ConstantPool> constantPools;
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

    private int interfaceCount;

    private int fieldsCount;

    private int constantInfoCount;

    public int getInterfaceCount() {
        return interfaceCount;
    }

    public void setInterfaceCount(int interfaceCount) {
        this.interfaceCount = interfaceCount;
    }

    public int getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(int fieldsCount) {
        this.fieldsCount = fieldsCount;
    }

    public int getConstantInfoCount() {
        return constantInfoCount;
    }

    public void setConstantInfoCount(int constantInfoCount) {
        this.constantInfoCount = constantInfoCount;
    }

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

    public List<ConstantPool> getConstantPools() {
        return constantPools;
    }

    public void setConstantPools(List<ConstantPool> constantPools) {
        this.constantPools = constantPools;
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

    @Override
    public String toString() {
        return "ClassFile{" +
                "magic='" + magic + '\'' +  '\n' +
                ", minorVersion='" + minorVersion + '\'' + '\n' +
                ", majorVersion='" + majorVersion + '\'' + '\n' +
                ", constantPool=" + constantPools + '\n' +
                ", accessFlags='" + accessFlags + '\'' + '\n' +
                ", thisClass='" + thisClass + '\'' + '\n' +
                ", superClass='" + superClass + '\'' + '\n' +
                ", interfaceClass='" + interfaceClass + '\'' + '\n' +
                ", fields=" + Arrays.toString(fields) + '\n' +
                ", methods=" + Arrays.toString(methods) + '\n' +
                ", attributes=" + Arrays.toString(attributes) + '\n' +
                ", interfaceCount=" + interfaceCount + '\n' +
                ", fieldsCount=" + fieldsCount + '\n' +
                ", constantInfoCount=" + constantInfoCount + '\n' +
                '}';
    }

    public TreeItem<String> getFileMsg(){

        final TreeItem<String> menu = new TreeItem<String>("ClassFile");
        final TreeItem<String> menu1 = new TreeItem<String>("magic:" + magic);
        final TreeItem<String> menu2 = new TreeItem<String>("minorVersion:" + minorVersion);
        final TreeItem<String> menu3 = new TreeItem<String>("majorVersion:" + majorVersion);
        final TreeItem<String> menu4 = new ConstantPool().getAllPool();
        final TreeItem<String> menu5 = new TreeItem<String>("accessFlags:" + accessFlags);
        final TreeItem<String> menu6 = new TreeItem<String>("thisClass:" + thisClass);
        final TreeItem<String> menu7 = new TreeItem<String>("superClass:" + superClass);
        final TreeItem<String> menu8 = new TreeItem<String>("interfaceClass:" + interfaceClass );
        final TreeItem<String> menu9 = new TreeItem<String>("fields:" + Arrays.toString(fields));
        final TreeItem<String> menu10 = new TreeItem<String>("methods:" + Arrays.toString(methods));
        final TreeItem<String> menu11 = new TreeItem<String>("attributes:" + Arrays.toString(attributes));
        final TreeItem<String> menu12 = new TreeItem<String>("interfaceCount:" + interfaceCount);
        final TreeItem<String> menu13 = new TreeItem<String>("fieldsCount:" + fieldsCount);
        final TreeItem<String> menu14 = new TreeItem<String>("constantInfoCount:" + constantInfoCount);
        menu.setExpanded(true);

        menu.getChildren().addAll(menu1,menu2,menu3,menu4,menu5,menu6,menu7,menu8,menu9,menu10,menu11,menu12,menu13,menu14);
        return menu;
    }
}
