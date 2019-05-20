package com.stylefeng.guns.api.film.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author
 * @description
 * @date 2019/5/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmInfo  implements Serializable {

    /**
     * filmId：’001’,
     * 			filmType：1, [0-2D,1-3D,2-3DIMAX,4-无]
     *             imgAddress：‘img/film/001.jpg’
     *             filmName：‘我不是药神’,
     * 			expectNum：283000,//受欢迎的人数
     * 			showTime : ‘2018-08-04’//上映的时间
     */

    private String filmId;
    private int filmType;
    private String imgAddress;
    private String filmName;
    private String filmScore;
    private int expectNum;//受欢迎的人数
    private String showTime;//上映时间
    private int boxNum;//票房
    private  String score;//分数

}
