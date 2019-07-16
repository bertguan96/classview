package org.classview.core.entity;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author gjt
 * @version 1.0
 * @date 2019/7/10 18:20
 * @Description 这里写描述内容
 */
public class ConstantPool {
    /**
     * 自增
     */
    private int id = 0;

    /**
     * 类似于 CONSTANT_Utf8_info
     * 常量成员类型
     */
    private String constantFlagName;
    /**
     * 成员变量
     * 一个hashmap 对应一个 类似于 u1 - 1
     */
    private List<HashMap> constantVal;
    /**
     * 对应的标志位ID
     */
    private int constantFlag;
    /**
     * 对应的地址
     */
    private String constantAddress;

    public String getConstantFlagName() {
        return constantFlagName;
    }

    public void setConstantFlagName(String constantFlagName) {
        setId(id++);
        this.constantFlagName = constantFlagName;
    }

    public List<HashMap> getConstantVal() {
        return constantVal;
    }

    public void setConstantVal(List<HashMap> constantVal) {
        this.constantVal = constantVal;
    }

    public int getConstantFlag() {
        return constantFlag;
    }

    public void setConstantFlag(int constantFlag) {
        this.constantFlag = constantFlag;
    }

    public String getConstantAddress() {
        return constantAddress;
    }

    public void setConstantAddress(String constantAddress) {
        this.constantAddress = constantAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ConstantPool{" + '\n' +
                "id=" + id + '\n' +
                ", constantFlagName='" + constantFlagName  + '\'' + '\n' +
                ", constantVal=" + constantVal + '\n' +
                ", constantFlag=" + constantFlag + '\n' +
                ", constantAddress='" + constantAddress + '\'' + '\n' +
                '}';
    }

    public TreeItem<String> getAllPool(){
        final TreeItem<String> menu = new TreeItem<String>("ConstantPool");
        final TreeItem<String> menu1 = new TreeItem<String>("id:" + id);
        final TreeItem<String> menu2 = new TreeItem<String>("constantFlagName:" + constantFlagName);
        final TreeItem<String> menu3 = getAllConstantVal();
        final TreeItem<String> menu4 = new TreeItem<String>("constantFlag:" + constantFlag);
        final TreeItem<String> menu5 = new TreeItem<String>("constantAddress:" + constantAddress);
        menu.setExpanded(true);
        menu.getChildren().addAll(menu1,menu2,menu3,menu4,menu5);
        return menu;

    }

    public TreeItem<String> getAllConstantVal(){
        final TreeItem<String> menu = new TreeItem<String>("constantVal");
        if(constantVal != null){
            for (HashMap val: constantVal){
                Map map = val;
                Iterator iter = map.entrySet().iterator();
                final TreeItem<String> menu0 = new TreeItem<String>(val.toString());
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    final TreeItem<String> menu1 = new TreeItem<String>(key + ": " + value);
                    menu0.getChildren().addAll(menu1);
                }
                menu.getChildren().addAll(menu0);
            }
        }
        menu.setExpanded(true);
        return menu;
    }
}
