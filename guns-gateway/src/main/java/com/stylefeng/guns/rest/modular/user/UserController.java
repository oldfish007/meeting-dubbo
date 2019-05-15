package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.UserInfoModel;
import com.stylefeng.guns.api.user.UserModel;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @description
 * @date 2019/5/15
 */
@RequestMapping("/user/")
@RestController
public class UserController {

    //暴露的接口
    @Reference(interfaceClass = UserAPI.class,check = false)
    private UserAPI userAPI;

    @RequestMapping(value="register",method = RequestMethod.POST)
    public ResponseVO register(UserModel userModel){
        //判断username password
        if(userModel.getUsername()==null || userModel.getUsername().trim().length()==0){
            ResponseVO.serviceFail("用户名不能为空");
        }
        if(userModel.getPassword()==null || userModel.getPassword().trim().length()==0){
            ResponseVO.serviceFail("密码不能为空");
        }
        boolean isSuccess = userAPI.register(userModel);
        if(isSuccess){
           return ResponseVO.success("注册成功");
        }else{
           return ResponseVO.serviceFail("注册失败");
        }
    }


    @RequestMapping(value="check",method = RequestMethod.POST)
    public ResponseVO check(UserModel userModel){
        //判断username
        if(userModel.getUsername()==null || userModel.getUsername().trim().length()==0){
          return   ResponseVO.serviceFail("用户名不能为空");
        }else{
            boolean isExist = userAPI.checkUsername(userModel.getUsername());
            if(isExist){
                return ResponseVO.serviceFail("用户名不存在");
            }else{
                return ResponseVO.serviceFail("用户名已经存在");
            }

        }
    }

    @RequestMapping(value="logout",method = RequestMethod.POST)
    public ResponseVO logout(UserModel userModel) {
 /*     应用：
                1、前端存储JWT 【七天】 ： JWT的刷新
                2、服务器端会存储活动用户信息【30分钟】
                3、JWT里的userId为key，查找活跃用户
            退出：
                1、前端删除掉JWT
                2、后端服务器删除活跃用户缓存
            现状：
                1、前端删除掉JWT
         */
        return ResponseVO.success("用户退出登录");
    }

    @RequestMapping(value="getUserInfo",method = RequestMethod.GET)
    public ResponseVO getUserInfo(){
       //取出threadlocal
        String userId = CurrentUser.getCurrentUser();
        if(userId !=null && userId.trim().length()>0){
            int uuid = Integer.parseInt(userId);
            UserInfoModel userInfo = userAPI.getUserInfo(uuid);
            if(userInfo!=null){
                return  ResponseVO.success(userInfo);
            }else{
                return ResponseVO.serviceFail("用户信息查询失败");
            }
        }else{
            return ResponseVO.serviceFail("用户未登录");
        }
    }
    @RequestMapping(value="updateUserInfo",method = RequestMethod.POST)
    public ResponseVO updateUserInfo(UserInfoModel userInfoModel){
        String userId = CurrentUser.getCurrentUser();
        if(userId!=null || userId.trim().length()>0){
            int uuid = Integer.parseInt(userId);
            // 判断当前登陆人员的ID与修改的结果ID是否一致
            if(uuid != userInfoModel.getUuid()){
                return ResponseVO.serviceFail("请修改您个人的信息");
            }
            //修改
            UserInfoModel userInfo = userAPI.updateUserInfo(userInfoModel);
            if(userInfo!=null){
                return ResponseVO.success(userInfo);
            }else{
                return ResponseVO.appFail("用户信息修改失败");
            }
        }else{
          return ResponseVO.serviceFail("用户未登录");
        }
    }
}
