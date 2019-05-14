package com.stylefeng.guns.api.user;

public interface UserAPI {
    public boolean login(String username, String password);

    boolean register(UserModel userModel);

    boolean checkUsername(String username);



}
