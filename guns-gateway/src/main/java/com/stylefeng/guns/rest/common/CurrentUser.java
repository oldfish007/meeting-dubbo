package com.stylefeng.guns.rest.common;

import com.stylefeng.guns.api.user.UserInfoModel;

public class CurrentUser {

    private static  final ThreadLocal<String> threadLocal = new ThreadLocal<>();
//将用户信息放入存储空
//一下这种方式不推荐使用 threadlocal每个线程都会创建
//JVM

    public static void saveUserId(String userId){
        threadLocal.set(userId);
    }
    public static String getCurrentUser(){
       return  threadLocal.get();
    }
/*    public static void saveUserInfo(UserInfoModel userInfoModel){
        threadLocal.set(userInfoModel);
    }
//将用户信息取出
    public static  UserInfoModel getUserInfo(){
        threadLocal.get();
    }*/
}
