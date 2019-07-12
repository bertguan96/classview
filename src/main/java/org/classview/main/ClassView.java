package org.classview.main;

import org.classview.core.BaseInfo;
import org.classview.core.ConstantInfo;
import org.classview.entity.ClassFile;
import org.classview.entity.ConstantMemberInfo;
import org.classview.entity.TagInfo;
import org.classview.utils.FileUtils;
import org.classview.utils.HexUtils;

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
        BaseInfo baseInfo = new BaseInfo();
        ClassFile classFile = baseInfo.baseInfoMessage(stringBytes);
//        String constantPoolNumbers = FileUtils.readBytesByIndex(stringBytes,9,10);
//        System.out.println("the  constant pool size is:" + (Integer.parseInt(constantPoolNumbers, 16)-1));
        ConstantInfo constantInfo = new ConstantInfo();
        constantInfo.getConstantInfo(stringBytes);
        System.out.println(FileUtils.readBytesByIndex(stringBytes,index-1,index));
        // 指针后指
        index+=1;
        System.out.println("this_class: " + FileUtils.readBytesByIndex(stringBytes,index,index+=1));
        // 指针后指
        index+=1;
        System.out.println("super_class: " + FileUtils.readBytesByIndex(stringBytes,index,index+=1));
        index+=1;
        int interfaceCount = Integer.parseInt(FileUtils.readBytesByIndex(stringBytes,index,index+=1), 16);
        System.out.println("interfaces_count: " + interfaceCount);
        if(interfaceCount == 0) {
            System.out.println("接口索引集合为空！");
            index+=1;
            int fieldsCount = Integer.parseInt(FileUtils.readBytesByIndex(stringBytes,index,index+=1), 16);
            System.out.println("fields_count:" + fieldsCount);
        }

    }

//    private void startReadConstantInfo(int startIndex,String bytes,int i) {
//        int tag = getTag(startIndex,bytes);
//        if(tag == 0) {
//            System.out.println("常量池读取完成");
//            // 如果标志位为0，那么直接结束
//            return;
//        }
//        System.out.println("第" + i + "个常量:");
//        System.out.println("标志位ID: " + tag);
//        ConstantMemberInfo memberSize = ConstantInfo.getConstantInfoIndex(tag);
//        System.out.println("常量标志名称：" + memberSize.getConstantName());
//        System.out.println("常量成员所占位置大小：" + memberSize.getConstantSize());
//        int index = ClassView.index;
//        ClassView.index += memberSize.getConstantSize() - 1;
//        System.out.println("开始位置:"+ index);
//        System.out.println("结束位置："+ClassView.index);
//        String str = FileUtils.readBytesByIndex(bytes,index,ClassView.index);
//        System.out.println("字段是：" + str);
//        String[] constantMemberTypes = memberSize.getConstantType().split(";");
//        int memberIndex = 0;
//        int poolSize = 1;
//        int u1Size = 0;
//        System.out.println("参数分析");
//        for (String menberType : constantMemberTypes) {
//            // 目前只支持U2
//            if(menberType.equals("u2")){
//
//                System.out.println("变量类型是:"+ menberType);
//                int typeSize = 4;
//                int u2Value = Integer.parseInt(str.substring(memberIndex,poolSize * typeSize),16);
//                System.out.println(u2Value);
//                memberIndex+=typeSize;
//                if(memberSize.getConstantType().contains("u1")) {
//                    u1Size = u2Value;
//                }
//                poolSize++;
//            }
//            if(menberType.equals("u1")){
//                System.out.println("变量类型是:"+ menberType);
//                int typeSize = 4;
//                ClassView.index = ClassView.index + u1Size - 1;
//                String newStr = FileUtils.readBytesByIndex(bytes,index,ClassView.index);
//                System.out.println("经过处理之后的新字符串是:"+newStr);
//                int strLen = newStr.length()/2;
//                System.out.println("类型是：utf-8,值是："+HexUtils.hexStr2Str(newStr.substring(memberIndex,poolSize * strLen)));
//                memberIndex+=typeSize;
//                poolSize++;
//            }
//            if(menberType.equals("u4")){
//                System.out.println("变量类型是:"+ menberType);
//                int typeSize = 8;
//                String u4Str = str.substring(memberIndex,poolSize * typeSize);
//                // 如果是float类型单独处理
//                if(memberSize.getConstantName().equals("CONSTANT_Float_info")) {
//                    System.out.println("数据类型是float，变量值:" + Float.intBitsToFloat(Integer.valueOf(u4Str,16)) + "f");
//                } else {
//                    System.out.println("变量值:" + Integer.parseInt(u4Str,16));
//                }
//                memberIndex+=typeSize;
//                poolSize++;
//            }
//            if(menberType.equals("u8")){
//                System.out.println("变量类型是:"+ menberType);
//                int typeSize = 16;
//                String u4Str = str.substring(memberIndex,poolSize * typeSize);
//                // 如果是float类型单独处理
//                if(memberSize.getConstantName().equals("CONSTANT_Double_info")) {
//                    System.out.println("数据类型是double,变量值:" + Double.longBitsToDouble(Long.parseLong(u4Str,16)) + "f");
//                } else {
//                    System.out.println("变量值:" +Long.parseLong(str.substring(memberIndex,poolSize * typeSize),16));
//                }
//
//                memberIndex+=typeSize;
//                poolSize++;
//            }
//        }
//        ClassView.index += 1;
//        System.out.println("");
//        System.out.println("执行完成，当前指针位置:" + ClassView.index);
//    }

//    /**
//     * 获得标志位
//     * @param index
//     * @param bytes
//     * @return
//     */
//    private int getTag(int index,String bytes) {
//        String tagAddr = FileUtils.readBytesByIndex(bytes,index,index);
//        int tagNumber = Integer.parseInt( tagAddr, 16);
//        ClassView.index = index + 1;
//        return tagNumber;
//    }

}

