package org.classview.main;

/**
 * @author gjt
 * @version 1.0
 * @date 2019/7/16 11:21
 * @Description 这里写描述内容
 */
public class MessageFactory {
    /**
     * 获取信息
     * @param name
     * @return
     */
    public Message getMessage(String name){
        if(name.equals("ClassView")) {
            return new ClassView();
        } else {
            System.out.println("初始化参数有误");
            return null;
        }
    }

}
