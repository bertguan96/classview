package org.classview;

import org.classview.core.api.ClassViewImpl;
import org.classview.core.api.ClassViewMessage;
import org.classview.core.entity.ClassFile;

import java.io.File;
import java.io.IOException;

/**
 * @author gjt
 * @version 1.0
 */
public class CreateMessageReader {
    /**
     * 获取信息
     */
    public static ClassViewMessage getMessage(String name){
        if(name.equals("ClassView")) {
            return new ClassViewImpl();
        } else {
            System.out.println("初始化参数有误");
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(args[0]);
        System.out.println(args[1]);

        // 解析工具
        if (args[0] != null && "ClassView".equals(args[0])) {
            File file = new File(args[1]);
            ClassFile a = (ClassFile) CreateMessageReader.getMessage(args[0]).getMessage(file);
            if(a != null) {
                System.out.println(a.toString());
            }
        }
    }
}
