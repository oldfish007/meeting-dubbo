package com.stylefeng.guns.rest.modular.film.vo;

import com.stylefeng.guns.api.film.vo.BannerVO;
import com.stylefeng.guns.api.film.vo.FilmInfo;
import com.stylefeng.guns.api.film.vo.FilmVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author
 * @description
 * @date 2019/5/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmIndexVO {

    private List<BannerVO> banners;//首页导航
    private FilmVO hotFilms;//热映
    private FilmVO soonFilms;//即将上映
    private List<FilmInfo> boxRanking;//票房
    private List<FilmInfo> expectRanking;//最受期待
    private List<FilmInfo> top100;//top100
}
