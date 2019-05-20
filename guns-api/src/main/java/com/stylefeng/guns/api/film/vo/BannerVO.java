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
public class BannerVO implements Serializable {

    private String bannerId;
    private String bannerAddress;
    private String bannerUrl;


}
