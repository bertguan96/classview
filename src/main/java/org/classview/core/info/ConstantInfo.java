package org.classview.core.info;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.classview.core.entity.ConstantMemberInfo;
import org.classview.core.entity.ConstantPool;
import org.classview.core.entity.TagInfo;
import org.classview.main.ClassView;
import org.classview.utils.FileUtils;
import org.classview.utils.HexUtils;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author gjt
 * @version 1.0
 * @date 2019/7/12 21:27
 * @Description 这里写描述内容
 */
public class ConstantInfo{


    // 存放常量的List数组
    static List<ConstantPool> constantPools = new LinkedList<>();

    /**
     *  获取常量池内容
     * @param bytes
     */
    public List<ConstantPool> getConstantInfo(String bytes ,String fileName) {
        // 获得常量池数量
        Integer constantPoolCount = constantPoolNumber(bytes) - 1;
        // 循环获取
        for (int i = 1; i < constantPoolCount; i++) {
            // 分析一下常量
            ConstantInfo.analyticalConstants(ClassView.index, bytes, i);
        }
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(constantPools));
        FileUtils.writeConstantInfoByJson(array.toJSONString(),new File(fileName + "_constant_info.json"));
        return constantPools;
    }

    /**
     * 分析解析常量对象
     * @param startIndex 开始索引
     * @param bytes  字符串
     * @param i  第几个（标志一下处理了几个常量了）
     */
    private static void analyticalConstants(int startIndex,String bytes,int i) {
        int tag = getTag(startIndex,bytes);
        if(tag == 0) {
            System.out.println("常量池读取完成");
            // 如果标志位为0，那么直接结束
            return;
        }
        ConstantPool constantPool = new ConstantPool();
        constantPool.setConstantFlag(tag);
        ConstantMemberInfo memberSize = ConstantInfo.getConstantInfoIndex(tag);
        constantPool.setConstantFlagName(memberSize.getConstantName());
        int index = ClassView.index;
        ClassView.index += memberSize.getConstantSize() - 1;
        String str = FileUtils.readBytesByIndex(bytes,index, ClassView.index);
        constantPool.setConstantAddress(str);
        String[] constantMemberTypes = memberSize.getConstantType().split(";");
        int memberIndex = 0;
        int poolSize = 1;
        int u1Size = 0;
        List<HashMap> constantVal = new LinkedList<>();
        for (String menberType : constantMemberTypes) {
            // 采用LinkedHashmap保证数据的有序性
            HashMap hashMap = new LinkedHashMap();
            if(menberType.equals("u2")){
                int typeSize = 4;
                int u2Value = Integer.parseInt(str.substring(memberIndex,poolSize * typeSize),16);
                hashMap.put("u2",u2Value);
                memberIndex+=typeSize;
                if(memberSize.getConstantType().contains("u1")) {
                    u1Size = u2Value;
                }
                poolSize++;
            }
            if(menberType.equals("u1")){
                int typeSize = 4;
                ClassView.index = ClassView.index + u1Size - 1;
                String newStr = FileUtils.readBytesByIndex(bytes,index, ClassView.index);
               // 这里地址重新赋值
                constantPool.setConstantAddress(newStr);
                int strLen = newStr.length()/2;
                String u1Value = HexUtils.hexStr2Str(newStr.substring(memberIndex,poolSize * strLen));
                hashMap.put("u1",u1Value);
                memberIndex+=typeSize;
                poolSize++;
            }
            if(menberType.equals("u4")){
                int typeSize = 8;
                String u4Str = str.substring(memberIndex,poolSize * typeSize);
                // 如果是float类型单独处理
                if(memberSize.getConstantName().equals("CONSTANT_Float_info")) {
                    float u4Value = Float.intBitsToFloat(Integer.valueOf(u4Str,16));
                    hashMap.put("u4",u4Value);
                } else {
                    Integer u4Value = Integer.parseInt(u4Str,16);
                    hashMap.put("u4",u4Value);
                }

                memberIndex+=typeSize;
                poolSize++;
            }
            if(menberType.equals("u8")){
                int typeSize = 16;
                String u4Str = str.substring(memberIndex,poolSize * typeSize);
                // 如果是float类型单独处理
                if(memberSize.getConstantName().equals("CONSTANT_Double_info")) {
                    double u8Value = Double.longBitsToDouble(Long.parseLong(u4Str,16));
                    hashMap.put("u8",u8Value);
                } else {
                    long u8Value =  Long.parseLong(str.substring(memberIndex,poolSize * typeSize),16);
                    hashMap.put("u8",u8Value);
                }
                memberIndex+=typeSize;
                poolSize++;
            }
            constantVal.add(hashMap);
        }
        constantPool.setConstantVal(constantVal);
        constantPools.add(constantPool);
        ClassView.index += 1;
    }

    /**
     * 获取常量池大小
     * @param bytes  16进制字符串
     * @return
     */
    private static Integer constantPoolNumber(String bytes) {
        Integer constantPoolNumber = Integer.parseInt(FileUtils.readBytesByIndex(bytes,9,10),16);
        return constantPoolNumber;
    }


    /**
     * 获得常量标签
     * @param index  开始位置
     * @param bytes  字符串（16进制）
     * @return
     */
    private static int getTag(int index,String bytes) {
        String tagAddr = FileUtils.readBytesByIndex(bytes,index,index);
        int tagNumber = Integer.parseInt( tagAddr, 16);
        ClassView.index = index + 1;
        return tagNumber;
    }

    /**
     * 根据标签取得对应的常量类型信息，名称，包含的数据类型等。
     * @param tag 常量池的标签
     * @return
     */
    private static ConstantMemberInfo getConstantInfoIndex(int tag) {
        ConstantMemberInfo constantMemberInfo = new ConstantMemberInfo();
        switch (tag) {
            case TagInfo.CONSTANT_UTF8:
                constantMemberInfo.setConstantName("CONSTANT_Utf8_info");
                constantMemberInfo.setConstantSize(3);
                constantMemberInfo.setConstantType("u2;u1;");
                return constantMemberInfo;
            case TagInfo.CONSTANT_INTEGER:
                constantMemberInfo.setConstantName("CONSTANT_Integer_info");
                constantMemberInfo.setConstantSize(4);
                constantMemberInfo.setConstantType("u4;");
                return constantMemberInfo;
            case TagInfo.CONSTANT_FLOAT:
                constantMemberInfo.setConstantName("CONSTANT_Float_info");
                constantMemberInfo.setConstantSize(4);
                constantMemberInfo.setConstantType("u4;");
                return constantMemberInfo;
            case TagInfo.CONSTANT_LONG:
                constantMemberInfo.setConstantName("CONSTANT_Long_info");
                constantMemberInfo.setConstantSize(8);
                constantMemberInfo.setConstantType("u8;");
                return constantMemberInfo;
            case TagInfo.CONSTANT_DOUBLE:
                constantMemberInfo.setConstantName("CONSTANT_Double_info");
                constantMemberInfo.setConstantSize(8);
                constantMemberInfo.setConstantType("u8;");
                return constantMemberInfo;
            case TagInfo.CONSTANT_CLASS:
                constantMemberInfo.setConstantName("CONSTANT_Class_info");
                constantMemberInfo.setConstantSize(2);
                constantMemberInfo.setConstantType("u2;");
                return constantMemberInfo;
            case TagInfo.CONSTANT_STRING:
                constantMemberInfo.setConstantName("CONSTANT_String_info");
                constantMemberInfo.setConstantSize(2);
                constantMemberInfo.setConstantType("u2;");
                return constantMemberInfo;
            case TagInfo.CONSTANT_FIELD_REF:
                constantMemberInfo.setConstantName("CONSTANT_Fieldref_info");
                constantMemberInfo.setConstantSize(4);
                constantMemberInfo.setConstantType("u2;u2;");
                return constantMemberInfo;
            case TagInfo.CONSTANT_METHOD_REF:
                constantMemberInfo.setConstantName("CONSTANT_Methodref_info");
                constantMemberInfo.setConstantSize(4);
                constantMemberInfo.setConstantType("u2;u2;");
                return constantMemberInfo;
            case TagInfo.CONSTANT_INTERFACE_METHOD_REF:
                constantMemberInfo.setConstantName("CONSTANT_InterfaceMethodref_info");
                constantMemberInfo.setConstantSize(4);
                constantMemberInfo.setConstantType("u2;u2;");
                return constantMemberInfo;
            case TagInfo.CONSTANT_NAME_AND_TYPE:
                constantMemberInfo.setConstantName("CONSTANT_NameAndType_info");
                constantMemberInfo.setConstantSize(4);
                constantMemberInfo.setConstantType("u2;u2;");
                return constantMemberInfo;
            default:
                System.out.println("常量池读取完毕！");
        }
        return null;
    }

}
