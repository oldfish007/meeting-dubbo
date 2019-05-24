package com.stylefeng.guns.api.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author
 * @description
 * @date 2019/5/24
 */
@Data
public class SourceVO implements Serializable {

    private String soureId;
    private String sourceName;
    private boolean isActive;
}
