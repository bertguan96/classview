package org.classview.core.entity.info;

public class FieldInfo {

    private String accessFlags;

    private Integer nameIndex;

    private Integer descriptorIndex;

    private Integer attributesCount;

    private AttributeInfo attributes;

    public String getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(String accessFlags) {
        this.accessFlags = accessFlags;
    }

    public Integer getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(Integer nameIndex) {
        this.nameIndex = nameIndex;
    }

    public Integer getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(Integer descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public Integer getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(Integer attributesCount) {
        this.attributesCount = attributesCount;
    }

    public AttributeInfo getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributeInfo attributes) {
        this.attributes = attributes;
    }
}
