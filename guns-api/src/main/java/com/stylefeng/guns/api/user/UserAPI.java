package com.stylefeng.guns.api.user;

import com.stylefeng.guns.api.user.vo.UserInfoModel;
import com.stylefeng.guns.api.user.vo.UserModel;

public interface UserAPI {
    public int login(String username, String password);
//用来注册
    boolean register(UserModel userModel);

    boolean checkUsername(String username);

    UserInfoModel getUserInfo(Integer uuid);

    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);

}