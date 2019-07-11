package org.classview.main;

import org.classview.entity.ClassFile;
import org.classview.entity.ConstantMemberInfo;
import org.classview.entity.TagInfo;
import org.classview.utils.FileUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author gjt
 * @version 1.0
 * @date 2019/7/10 18:25
 * @Description 这里写描述内容
 */
public class ClassView {


    public static int index = 11;

    public static int id = 1;

    private String getStrings( List<String> bytes){
        StringBuffer stringBuffer = new StringBuffer();
        for(String byte1:bytes){
            stringBuffer.append(byte1);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) throws IOException {
        String filepath = "C:\\Users\\gjt\\Desktop\\ClassFileTest.class";
        List<String> bytes =  FileUtils.readClassFile(filepath);
        System.out.println(bytes);
        ClassView classView = new ClassView();
        String stringBytes = classView.getStrings(bytes);
        ClassFile classFile = new ClassFile();
//        System.out.println(stringBytes);
//        classFile.setMagic(FileUtils.readBytesByIndex(stringBytes,1,4));
//        classFile.setMinorVersion(FileUtils.readBytesByIndex(stringBytes,5,6));
//        classFile.setMajorVersion(FileUtils.readBytesByIndex(stringBytes,7,8));
//        System.out.println("the magic number is:" + FileUtils.readBytesByIndex(stringBytes,1,4));
//        System.out.println("the minor number is:" + FileUtils.readBytesByIndex(stringBytes,5,6));
//        System.out.println("the major number is:" + FileUtils.readBytesByIndex(stringBytes,7,8));
//        String constantPoolNumbers = FileUtils.readBytesByIndex(stringBytes,9,10);
//        System.out.println("the constant pool size is:" + (Integer.parseInt(constantPoolNumbers, 16)-1));
//        System.out.println(FileUtils.readBytesByIndex(stringBytes,17,17));
        classView.startReadConstantInfo(ClassView.index,stringBytes);
        classView.startReadConstantInfo(ClassView.index,stringBytes);
        classView.startReadConstantInfo(ClassView.index,stringBytes);
        classView.startReadConstantInfo(ClassView.index,stringBytes);
        classView.startReadConstantInfo(ClassView.index,stringBytes);
        classView.startReadConstantInfo(ClassView.index,stringBytes);
        classView.startReadConstantInfo(ClassView.index,stringBytes);
        classView.startReadConstantInfo(ClassView.index,stringBytes);
        classView.startReadConstantInfo(ClassView.index,stringBytes);
        classView.startReadConstantInfo(ClassView.index,stringBytes);
        classView.startReadConstantInfo(ClassView.index,stringBytes);
        classView.startReadConstantInfo(ClassView.index,stringBytes);
        System.out.println();
    }

    private void startReadConstantInfo(int startIndex,String bytes) {
        int tag = getTag(startIndex,bytes);
        System.out.println("标志位ID: " + tag);
        ConstantMemberInfo memberSize = getConstantInfoIndex(tag);
        System.out.println("常量标志名称：" + memberSize.getConstantName());
        System.out.println("常量成员所占位置大小：" + memberSize.getConstantSize());
        int index = ClassView.index;
        ClassView.index += memberSize.getConstantSize() - 1;
        System.out.println("开始位置:"+ index);
        System.out.println("结束位置："+ClassView.index);
        String str = FileUtils.readBytesByIndex(bytes,index,ClassView.index);
        System.out.println("字段是：" + str);
        String[] constantMemberTypes = memberSize.getConstantType().split(";");
        int memberIndex = 0;
        int poolSize = 1;
        for (String menberType : constantMemberTypes) {
            // 目前只支持U2
            if(menberType.equals("u2")){
                System.out.println("变量类型是:"+ menberType);
                int typeSize = 4;
                System.out.println(Integer.parseInt(str.substring(memberIndex,poolSize * typeSize),16));
                memberIndex+=typeSize;
                poolSize++;
            }
            if(menberType.equals("u1")){
                System.out.println("变量类型是:"+ menberType);
                int typeSize = 3;
                System.out.println(Integer.parseInt(str.substring(memberIndex,poolSize * typeSize),16));
                memberIndex+=typeSize;
                poolSize++;
            }
            if(menberType.equals("u4")){
                System.out.println("变量类型是:"+ menberType);
                int typeSize = 8;
                System.out.println(Integer.parseInt(str.substring(memberIndex,poolSize * typeSize),16));
                memberIndex+=typeSize;
                poolSize++;
            }
            if(menberType.equals("u8")){
                System.out.println("变量类型是:"+ menberType);
                int typeSize = 16;
                System.out.println(Integer.parseInt(str.substring(memberIndex,poolSize * typeSize),16));
                memberIndex+=typeSize;
                poolSize++;
            }
        }
        ClassView.index += 1;
        System.out.println("");
        System.out.println("当前指针位置:" + ClassView.index);
    }

    /**
     * 获得标志位
     * @param index
     * @param bytes
     * @return
     */
    private int getTag(int index,String bytes) {
        String tagAddr = FileUtils.readBytesByIndex(bytes,index,index);
        System.out.println("tag addr is： " + tagAddr);
        int tagNumber = Integer.parseInt( tagAddr, 16);
        ClassView.index = index + 1;
        return tagNumber;
    }

    private ConstantMemberInfo getConstantInfoIndex(int tag) {
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
        return constantMemberInfo;
    }
}

