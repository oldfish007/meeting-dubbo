package com.stylefeng.guns.api.film.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author
 * @description
 * @date 2019/5/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmVO implements Serializable {

    private int filmNum;
    private List<FilmInfo> filmInfo;
}
