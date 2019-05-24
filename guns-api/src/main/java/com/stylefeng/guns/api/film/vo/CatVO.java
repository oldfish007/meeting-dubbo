package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author
 * @description
 * @date 2019/5/24
 */
@Data
public class CatVO implements Serializable {

    private String catId;
    private String catName;
    private boolean isActive;
}
