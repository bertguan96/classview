package org.classview.core.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gjt
 * @version 1.0
 * @date 2019/7/12 13:16
 * @Description flag常量
 */
public class AccFlag {

    private static Map<String, String> accFlagMap = new HashMap<>();

    static  {
        accFlagMap.put("0001", "ACC_PUBLIC");
        accFlagMap.put("0010", "ACC_FINAL");
        accFlagMap.put("0020", "ACC_SUPER");
        accFlagMap.put("0200", "ACC_INTERFACE");
        accFlagMap.put("0400", "ACC_ABSTRACT");
        accFlagMap.put("1000", "ACC_SYNTHETIC");
        accFlagMap.put("2000", "ACC_ANNOTATION");
        accFlagMap.put("4000", "ACC_ENUM");

        accFlagMap.put("0002", "ACC_PRIVATE");
        accFlagMap.put("0004", "ACC_PROTECTED");
        accFlagMap.put("0008", "ACC_STATIC");

        accFlagMap.put("0040", "ACC_VOLATILE");
        accFlagMap.put("0080", "ACC_TRANSIENT");
    }

    public static String getAccFlag(String attr) {
        return accFlagMap.get(attr);
    }
}
