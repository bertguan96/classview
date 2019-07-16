package org.classview.main;

import org.classview.core.entity.ClassFile;

import java.io.File;
import java.io.IOException;

/**
 * @author gjt
 * @version 1.0
 * @date 2019/7/16 11:16
 * @Description 这里写描述内容
 */
public interface Message {
    /**
     * 获取基础信息
     * @param file
     * @return
     */
    Object getMessage(File file) throws IOException;
}
