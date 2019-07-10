package org.classview.main;

import org.classview.entity.ClassFile;
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
        System.out.println(stringBytes);
        classFile.setMagic(FileUtils.readBytesByIndex(stringBytes,1,4));
        classFile.setMinorVersion(FileUtils.readBytesByIndex(stringBytes,5,6));
        classFile.setMajorVersion(FileUtils.readBytesByIndex(stringBytes,7,8));
        System.out.println("the magic number is:" + FileUtils.readBytesByIndex(stringBytes,1,4));
        System.out.println("the minor number is:" + FileUtils.readBytesByIndex(stringBytes,5,6));
        System.out.println("the major number is:" + FileUtils.readBytesByIndex(stringBytes,7,8));
        String constantPoolNumbers = FileUtils.readBytesByIndex(stringBytes,9,10);
        System.out.println("the constant pool size is:" + (Integer.parseInt(constantPoolNumbers, 16)-1));
    }

}

