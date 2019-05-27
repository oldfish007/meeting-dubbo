package com.stylefeng.guns.api.film;

import com.stylefeng.guns.api.film.vo.BannerVO;
import com.stylefeng.guns.api.film.vo.CatVO;
import com.stylefeng.guns.api.film.vo.FilmInfo;
import com.stylefeng.guns.api.film.vo.FilmVO;

import java.util.List;

public interface FilmServiceAPI {

    //获取banners
    List<BannerVO> getBanners();
    //获取热映影片
    FilmVO getHotFilms(boolean isLimit, int nums);
    //获取即将上映的影片【受欢迎程度做排行】
    FilmVO getSoonFilms(boolean isLimit,int nums);
    //获取票房排行榜
    List<FilmInfo> getBoxRanking();
    //获取人气排行榜
    List<FilmInfo> getExpertRanking();
    //获取top100
    List<FilmInfo> getTop();

    //分类条件
    List<CatVO> getCats();
    //片源条件

}
