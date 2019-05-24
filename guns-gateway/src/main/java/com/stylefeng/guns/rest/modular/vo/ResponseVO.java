package com.stylefeng.guns.rest.modular.vo;

import lombok.Data;

@Data
public class ResponseVO<M> {
 // 0 成功 1 业务失败  999 系统异常
    private int status;

    private String message;

    private M data;

    private String imgPre;

    public ResponseVO() {
    }

    public static<M> ResponseVO success(M m){
         ResponseVO responseVO = new ResponseVO();
         responseVO.setStatus(0);
         responseVO.setData(m);
         return responseVO;

    }

    public static<M> ResponseVO success(String imgPre,M m){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(m);
        responseVO.setImgPre(imgPre);
        return responseVO;

    }

    public static<M> ResponseVO success(String message){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setMessage(message);
        return responseVO;

    }

//业务错误
    public static<M> ResponseVO serviceFail(String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setMessage(msg);
        return responseVO;
    }
//系统错误
public static<M> ResponseVO appFail(String msg){
    ResponseVO responseVO = new ResponseVO();
    responseVO.setStatus(999);
    responseVO.setMessage(msg);
    return responseVO;
}
}
