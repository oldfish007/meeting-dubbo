package com.stylefeng.guns.api.user;

public interface UserAPI {
    public int login(String username, String password);
//用来注册
    boolean register(UserModel userModel);

    boolean checkUsername(String username);

    UserInfoModel getUserInfo(Integer uuid);

    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);

}