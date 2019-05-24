package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.rest.modular.film.vo.FilmIndexVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @description
 * @date 2019/5/16
 */
@RestController
@RequestMapping("/film/")
public class FilmController {


    private static final String IMG_PRE="www.meetingfilm.com";
    //获取首页信息接口
     @Reference(interfaceClass = FilmServiceAPI.class)
     private  FilmServiceAPI filmServiceAPI;
    /**
     * API 【聚合】
     * 这里只是一层中转  这里不会设计很多层 就是很薄的一层
     * @return
     */
    @RequestMapping(value="getIndex",method = RequestMethod.GET)
    public ResponseVO<FilmIndexVO> getIndex(){

        FilmIndexVO filmIndexVO = new FilmIndexVO();
        //获取banner信息
        filmIndexVO.setBanners(filmServiceAPI.getBanners());
        //获取热映电影 这里很多人认为只是一个接口就好 通过状态区分 这样就把设计暴露给了前端 多就是少
        filmIndexVO.setHotFilms(filmServiceAPI.getHotFilms(true,8));
        //即将上映电影
        filmIndexVO.setSoonFilms(filmServiceAPI.getSoonFilms(true,8));
        //票房排行榜
        filmIndexVO.setBoxRanking(filmServiceAPI.getBoxRanking());
        //受欢迎人气榜
        filmIndexVO.setExpectRanking(filmServiceAPI.getExpertRanking());
        //top100
        filmIndexVO.setTop100(filmServiceAPI.getTop());

        return ResponseVO.success(IMG_PRE,filmIndexVO);
    }

    @RequestMapping(value="getConditionList",method = RequestMethod.GET)
    public ResponseVO getConditionList( @RequestParam(name="catId",required = false,defaultValue = "99") String catId,
                                        @RequestParam(name="sourceId",required = false,defaultValue = "99")String sourceId,
                                        @RequestParam(name="yearId",required = false,defaultValue = "99")String yearId){
        //类型集合

        //片源集合

        //年代集合

        return null;
    }

}
