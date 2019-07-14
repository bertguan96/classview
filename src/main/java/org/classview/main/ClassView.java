package org.classview.main;

import org.classview.core.info.BaseInfo;
import org.classview.core.info.ConstantInfo;
import org.classview.core.entity.ClassFile;
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

    private String getStrings( List<String> bytes){
        StringBuffer stringBuffer = new StringBuffer();
        for(String byte1:bytes){
            stringBuffer.append(byte1);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            try {
                throw new Exception("请填写需要解析的class文件的地址！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        String filepath = "C:\\Users\\gjt\\Desktop\\ClassFileTest.class";
        String filepath = args[0];
        // 分割输入的路径
        String[] filePaths = filepath.split("\\\\");
        // 提取文件名称
        String fileName = filePaths[filePaths.length - 1].split("\\.")[0];
        List<String> bytes =  FileUtils.readClassFile(filepath);
        ClassView classView = new ClassView();
        String stringBytes = classView.getStrings(bytes);
        BaseInfo baseInfo = new BaseInfo();
        ClassFile classFile = baseInfo.baseInfoMessage(stringBytes);
        ConstantInfo constantInfo = new ConstantInfo();
        constantInfo.getConstantInfo(stringBytes,fileName);
        System.out.println("access_flags: " +FileUtils.readBytesByIndex(stringBytes,index-1,index));
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
            index+=1;
            int fieldsCount = Integer.parseInt(FileUtils.readBytesByIndex(stringBytes,index,index+=1), 16);
            System.out.println("fields_count:" + fieldsCount);
        }

    }

}

