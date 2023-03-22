package com.itheima.reggie.common;

public class BaseContext {
    private static ThreadLocal<Long> threadId=new ThreadLocal<>();
    public static void setCurrentId(Long id){
        threadId.set(id);
    }
    public static Long getCurrentId(){
        return threadId.get();
    }

}
