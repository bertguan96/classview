package org.classview.core.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author gjt
 * @version 1.0
 * @date 2019/7/16 11:16
 * @Description 这里写描述内容
 */
public interface ClassViewMessage {
    /**
     * 获取基础信息
     * @param file
     * @return
     */
    Object getMessage(File file) throws IOException;

    /**
     * 获取字符串
     * @param bytes
     * @return
     */
    String getStrings(List<String> bytes);
}
