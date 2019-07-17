package org.classview.main;

import org.classview.core.entity.ConstantPool;
import org.classview.core.info.BaseInfo;
import org.classview.core.info.ConstantInfo;
import org.classview.core.entity.ClassFile;
import org.classview.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author gjt
 * @version 1.0
 * @date 2019/7/10 18:25
 * @Description 这里写描述内容
 */
public class ClassView implements Message {

    // 默认从11开始，因为之前几个都是固定的。。
    public static int index = 11;

    @Override
    public String getStrings( List<String> bytes){
        StringBuffer stringBuffer = new StringBuffer();
        for(String byte1:bytes){
            stringBuffer.append(byte1);
        }
        return stringBuffer.toString();
    }
    @Override
    public ClassFile getMessage(File file) throws IOException {
        String filepath = file.getPath();
        if(filepath.length() == 0) {
            try {
                throw new Exception("请填写需要解析的class文件的地址！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        List<ConstantPool> constantPools = constantInfo.getConstantInfo(stringBytes,fileName);
        classFile.setConstantPools(constantPools);
        classFile.setAccessFlags(FileUtils.readBytesByIndex(stringBytes,index-1,index));
        // 指针后指
        index+=1;
        classFile.setThisClass(FileUtils.readBytesByIndex(stringBytes,index,index+=1));
        // 指针后指
        index+=1;
        classFile.setSuperClass(FileUtils.readBytesByIndex(stringBytes,index,index+=1));
        index+=1;
        classFile.setInterfaceCount(Integer.parseInt(FileUtils.readBytesByIndex(stringBytes,index,index+=1), 16));
        int interfaceCount = classFile.getInterfaceCount();
        if(interfaceCount == 0) {
            index+=1;
            int fieldsCount = Integer.parseInt(FileUtils.readBytesByIndex(stringBytes,index,index+=1), 16);
            classFile.setFieldsCount(fieldsCount);
            index+=1;
        }
        return classFile;

    }

}

