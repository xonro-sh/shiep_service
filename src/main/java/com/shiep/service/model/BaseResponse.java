package com.shiep.service.model;

/**
 * 基础响应模型
 * @author louie
 * @date created in 2018-4-26 14:54
 */
public class BaseResponse {
    public BaseResponse(){

    }

    public BaseResponse(boolean ok,String message){
        this.ok = ok;
        this.message = message;
    }
    private boolean ok;
    private String message;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
