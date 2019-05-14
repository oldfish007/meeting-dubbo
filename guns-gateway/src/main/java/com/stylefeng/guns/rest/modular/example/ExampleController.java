package com.stylefeng.guns.rest.modular.example;

import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.common.SimpleObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 常规控制器
 *
 * @author fengshuonan
 * @date 2017-08-23 16:02
 */
@Controller
@RequestMapping("/hello")
public class ExampleController {

    @RequestMapping("")
    public ResponseEntity hello() {
        System.out.println(CurrentUser.getCurrentUser());
        //使用 userId -> key -> redis[userInfo] ->30分钟 一般是30分钟左右
        //第二次访问的时候 就去监测redis里面有没有
        //redis里面有 就不动它

        return ResponseEntity.ok("请求成功!");
    }
}
