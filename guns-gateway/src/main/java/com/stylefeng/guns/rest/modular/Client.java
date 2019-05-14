package com.stylefeng.guns.rest.modular;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.user.UserAPI;

/**
 * @author
 * @description测试用户暴露接口
 * @date 2019/5/14
 */
public class Client {

    @Reference(interfaceClass = UserAPI.class)
    private UserAPI userAPI;

    public void run(){
        System.out.println(userAPI.login("admin","123"));
    }
}
