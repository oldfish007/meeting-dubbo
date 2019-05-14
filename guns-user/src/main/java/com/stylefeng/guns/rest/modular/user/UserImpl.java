package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;

import com.stylefeng.guns.api.user.UserAPI;
import org.springframework.stereotype.Component;

/**
 * @author
 * @description
 * @date 2019/5/14
 */
@Component
@Service(interfaceClass = UserAPI.class) //相当于把服务暴露出去
public class UserImpl implements UserAPI {
    @Override
    public boolean login(String username, String password) {
        System.out.println("this is user service!!"+username+", "+password);

        return false ;
    }
}
