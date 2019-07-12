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

}

