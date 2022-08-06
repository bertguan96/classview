package org.classview.core.api;

import org.classview.core.entity.ConstantPool;
import org.classview.core.entity.Interfaces;
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
 */
public class ClassViewImpl implements ClassViewMessage {

    // 默认从11开始，因为之前几个都是固定的。。
    public static int index = 11;

    @Override
    public String getStrings( List<String> bytes){
        StringBuilder buffer = new StringBuilder();
        for(String byte1:bytes){
            buffer.append(byte1);
        }
        return buffer.toString();
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
        ClassViewImpl classViewImpl = new ClassViewImpl();
        String stringBytes = classViewImpl.getStrings(bytes);
        BaseInfo baseInfo = new BaseInfo();
        ClassFile classFile = baseInfo.baseInfoMessage(stringBytes);
        ConstantInfo constantInfo = new ConstantInfo();
        List<ConstantPool> constantPools = constantInfo.getConstantInfo(stringBytes,fileName);
        classFile.setConstantPools(constantPools);
        classFile.setAccessFlags(FileUtils.readBytesByIndex(stringBytes,index-1,index));
        index+=1;
        classFile.setThisClass(FileUtils.readBytesByIndex(stringBytes,index,index+=1));
        index+=1;
        classFile.setSuperClass(FileUtils.readBytesByIndex(stringBytes,index,index+=1));
        index+=1;
        System.out.println(index);
        classFile.setInterfaceCount(Integer.parseInt(FileUtils.readBytesByIndex(stringBytes,index,index+=1), 16));
        int interfaceCount = classFile.getInterfaceCount();
        if(interfaceCount == 0) {
            index+=1;
            int fieldsCount = Integer.parseInt(FileUtils.readBytesByIndex(stringBytes,index,index+=1), 16);
            classFile.setFieldsCount(fieldsCount);
            index+=1;
        } else {
            // todo读取接口信息
            Interfaces[] = FileUtils.readBytesByIndex(stringBytes, index, interfaceCount+=1);
            System.out.println(index);
        }
        return classFile;
    }
}

