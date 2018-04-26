package com.shiep.service.service.impl;

import com.shiep.service.service.SecurityService;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author louie
 * @date created in 2018-4-25 15:40
 */
public class SecurityServiceImpl implements SecurityService {

    /**
     * 加密key
     */
    private String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean verify(String timestamp, String token) {
        StringBuilder resource = new StringBuilder();
        resource.append("key=").append(key).append("&timestamp=").append(timestamp);
        String decoded = DigestUtils.md5Hex(resource.toString()).toLowerCase();
        if (decoded.equals(token)){
            return true;
        }
        return false;
    }
}
