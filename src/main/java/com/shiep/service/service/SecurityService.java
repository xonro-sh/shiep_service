package com.shiep.service.service;

/**
 * 安全认证接口
 * @author louie
 * @date created in 2018-4-25 15:38
 */
public interface SecurityService {
    /**
     * 接口调用安全认证
     * @param timestamp 时间戳
     * @param token
     * @return
     */
    boolean verify(String timestamp,String token);
}
