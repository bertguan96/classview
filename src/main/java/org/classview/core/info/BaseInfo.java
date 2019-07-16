package org.classview.core.info;

import org.classview.core.entity.ClassFile;
import org.classview.utils.FileUtils;

/**
 * @author gjt
 * @version 1.0
 * @date 2019/7/12 21:32
 * @Description 基础信息
 */
public class BaseInfo {


    public ClassFile baseInfoMessage(String bytes) {
        ClassFile baseInfo = new ClassFile();
        baseInfo.setMagic(magicNo(bytes));
        baseInfo.setMinorVersion(String.valueOf(Integer.parseInt(minorVersion(bytes),16)));
        baseInfo.setMajorVersion(String.valueOf(Integer.parseInt(majorVersion(bytes),16)));
        return baseInfo;
    }

    /**
     * 魔数
     *
     * @param bytes 十六进制字符串
     * @return
     */
    private static String magicNo(String bytes) {
        return FileUtils.readBytesByIndex(bytes, 1, 4);
    }

    /**
     * 小版本
     * @param bytes
     * @return
     */
    private static String minorVersion(String bytes) {
        return FileUtils.readBytesByIndex(bytes,5,6);
    }

    /**
     * 大版本
     * @param bytes
     * @return
     */
    private static String majorVersion(String bytes) {
        return FileUtils.readBytesByIndex(bytes,7,8);
    }


}
