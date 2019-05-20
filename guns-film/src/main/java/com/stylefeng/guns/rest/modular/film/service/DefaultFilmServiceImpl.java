package com.stylefeng.guns.rest.modular.film.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.BannerVO;
import com.stylefeng.guns.api.film.vo.FilmInfo;
import com.stylefeng.guns.api.film.vo.FilmVO;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.common.persistence.dao.MoocBannerTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MoocFilmTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocBannerT;
import com.stylefeng.guns.rest.common.persistence.model.MoocFilmT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @description
 * @date 2019/5/20
 */
@Component
@Service(interfaceClass = FilmServiceAPI.class)
public class DefaultFilmServiceImpl implements FilmServiceAPI {

    @Autowired
    private MoocBannerTMapper moocBannerTMapper;
    @Autowired
    private MoocFilmTMapper moocFilmTMapper;
    @Override
    public List<BannerVO> getBanners() {

        List<MoocBannerT> moocBanners = moocBannerTMapper.selectList(null);
        List<BannerVO> result = new ArrayList<>();
        for (MoocBannerT moocBannerT:
        moocBanners) {
            BannerVO bannerVO = new BannerVO();
            bannerVO.setBannerId(moocBannerT.getUuid()+"");
            bannerVO.setBannerUrl(moocBannerT.getBannerUrl());
            bannerVO.setBannerAddress(moocBannerT.getBannerAddress());
            result.add(bannerVO);
        }
        return result ;
    }
// 热映影片  这个limit 到底是首页的8张 还是后面的列表页
    @Override
    public FilmVO getHotFilms(boolean isLimit, int nums) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();
        //判断是否是首页需要的内容
        EntityWrapper<MoocFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");
        //如果是则限制条数 限制内容为热映影片
        if(isLimit){
            Page<MoocFilmT> page = new Page<>(1,nums);// 1页  每页显示条数前端传进来
            List<MoocFilmT> moocFilms = moocFilmTMapper.selectPage(page, entityWrapper);
            //组织filmInfo
            filmInfos = getFilmInfos(moocFilms);
            filmVO.setFilmNum(moocFilms.size());
            filmVO.setFilmInfo(filmInfos);
        }else{

        }
        //如果不是则是列表页 同样需要限制为热映影片
        return filmVO;
    }

    private List<FilmInfo> getFilmInfos(List<MoocFilmT> moocFilms){
        List<FilmInfo> filmInfos = new ArrayList<>();
        for(MoocFilmT moocFilmT : moocFilms){
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setScore(moocFilmT.getFilmScore());
            filmInfo.setImgAddress(moocFilmT.getImgAddress());
            filmInfo.setFilmType(moocFilmT.getFilmType());
            filmInfo.setFilmScore(moocFilmT.getFilmScore());
            filmInfo.setFilmName(moocFilmT.getFilmName());
            filmInfo.setFilmId(moocFilmT.getUuid()+"");
            filmInfo.setExpectNum(moocFilmT.getFilmPresalenum());
            filmInfo.setBoxNum(moocFilmT.getFilmBoxOffice());
            filmInfo.setShowTime(DateUtil.getDay(moocFilmT.getFilmTime()));
            // 将转换的对象放入结果集
            filmInfos.add(filmInfo);
        }
        return filmInfos;
    }

//即将上映的影片
    @Override
    public FilmVO getSoonFilms(boolean isLimit, int nums) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();
        //判断是否是首页需要的内容
        EntityWrapper<MoocFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","2");
        //如果是则限制条数 限制内容为热映影片
        if(isLimit){
            Page<MoocFilmT> page = new Page<>(1,nums);// 1页  每页显示条数前端传进来
            List<MoocFilmT> moocFilms = moocFilmTMapper.selectPage(page, entityWrapper);
            //组织filmInfo
            filmInfos = getFilmInfos(moocFilms);
            filmVO.setFilmNum(moocFilms.size());
            filmVO.setFilmInfo(filmInfos);
        }else{

        }
        //如果不是则是列表页 同样需要限制为热映影片
        return filmVO;
    }
//票房
    @Override
    public List<FilmInfo> getBoxRanking() {
        //条件->正在上映的，票房前10名
        EntityWrapper<MoocFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");
        Page<MoocFilmT> page = new Page<>(1,10,"film_box_office");
        List<MoocFilmT> moocFilms = moocFilmTMapper.selectPage(page, entityWrapper);
        List<FilmInfo> filmInfos = getFilmInfos(moocFilms);
        return filmInfos;
    }

    @Override
    public List<FilmInfo> getExpertRanking() {
        //条件->即将上映的，预售前10名
        EntityWrapper<MoocFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","2");
        Page<MoocFilmT> page = new Page<>(1,10,"film_preSaleNum ");
        List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, entityWrapper);
        List<FilmInfo> filmInfos = getFilmInfos(moocFilmTS);
        return filmInfos;
    }

    @Override
    public List<FilmInfo> getTop() {
        //条件-》正在上映的经典影片，打分前10名
        EntityWrapper<MoocFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");
        Page<MoocFilmT> page = new Page<>(1,10,"film_score");
        List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, entityWrapper);
        List<FilmInfo> filmInfos = getFilmInfos(moocFilmTS);
        return filmInfos;
    }
}
