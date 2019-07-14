package org.classview.core.entity;

/**
 * @author gjt
 * @version 1.0
 * @date 2019/7/12 0:14
 * @Description 常量池实体
 */
public class ConstantMemberInfo {

    /**
     *  常量类型
     */
    private String constantName;
    /**
     * 常量所占位数
     */
    private int constantSize;
    /**
     * 常量的类型（u1,u2,u4,u8）
     * 多个类型以字符串隔开
     */
    private String constantType;

    public String getConstantName() {
        return constantName;
    }

    public void setConstantName(String constantName) {
        this.constantName = constantName;
    }

    public int getConstantSize() {
        return constantSize;
    }

    public void setConstantSize(int constantSize) {
        this.constantSize = constantSize;
    }

    public String getConstantType() {
        return constantType;
    }

    public void setConstantType(String constantType) {
        this.constantType = constantType;
    }
}
