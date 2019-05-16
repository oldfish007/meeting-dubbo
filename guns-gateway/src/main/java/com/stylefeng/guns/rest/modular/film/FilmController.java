package com.stylefeng.guns.rest.modular.film;

import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @description
 * @date 2019/5/16
 */
@RestController
@RequestMapping("/film/")
public class FilmController {


    //获取首页信息接口

    /**
     * API 【聚合】
     *
     * @return
     */
    @RequestMapping(value="getIndex",method = RequestMethod.GET)
    public ResponseVO getIndex(){
        //获取banner信息

        //获取热映电影

        //即将上映电影

        //票房排行榜

        //受欢迎人气榜

        //top100
        return null;
    }
}
