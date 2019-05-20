package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.rest.modular.film.vo.FilmIndexVO;
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
     @Reference(interfaceClass = FilmServiceAPI.class)
     private  FilmServiceAPI filmServiceAPI;
    /**
     * API 【聚合】
     *
     * @return
     */
    @RequestMapping(value="getIndex",method = RequestMethod.GET)
    public ResponseVO<FilmIndexVO> getIndex(){

        FilmIndexVO filmIndexVO = new FilmIndexVO();
        //获取banner信息
        filmIndexVO.setBanners(filmServiceAPI.getBanners());
        //获取热映电影
        filmIndexVO.setHotFilms(filmServiceAPI.getHotFilms(true,8));
        //即将上映电影
        filmIndexVO.setSoonFilms(filmServiceAPI.getSoonFilms(true,8));
        //票房排行榜
        filmIndexVO.setBoxRanking(filmServiceAPI.getBoxRanking());
        //受欢迎人气榜
        filmIndexVO.setExpectRanking(filmServiceAPI.getExpertRanking());
        //top100
        filmIndexVO.setTop100(filmServiceAPI.getTop());
        return ResponseVO.success(filmIndexVO);
    }
}
