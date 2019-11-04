package com.gyf;

import java.io.Serializable;

/**
 * created by guoyanfei on 2019/11/1
 * 3、使用私有构造方法或枚举实现Singleton属性
 */
public class Elvis implements Serializable {
    private static final Elvis INSTANCE = new Elvis();
    private Elvis(){}
    public static  Elvis getInstance(){
        return INSTANCE;
    }

    //设置可序列化
    private Object readResolve(){
        return INSTANCE;
    }

}
