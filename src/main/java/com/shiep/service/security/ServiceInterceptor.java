package com.shiep.service.security;

import com.google.common.base.Strings;
import com.shiep.service.service.SecurityService;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务安全认证拦截器
 * @author louie
 * @date created in 2018-4-25 16:13
 */
public class ServiceInterceptor extends AbstractPhaseInterceptor<Message> {
    public ServiceInterceptor(String phase){
        super(phase);
    }

    public ServiceInterceptor(){
        super(Phase.PRE_INVOKE);
    }

    private SecurityService securityService;

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        String requestParams = (String) message.get(Message.QUERY_STRING);
        if (Strings.isNullOrEmpty(requestParams)){
            throw new Fault(new IllegalArgumentException("request params can not be empty"));
        }

        Map<String,String> paramMap = getParamsMap(requestParams);
        String token = paramMap.get("token");
        String timestamp = paramMap.get("timestamp");

        if (Strings.isNullOrEmpty(token)){
            throw new Fault(new IllegalArgumentException("token can not be empty"));
        }

        if (Strings.isNullOrEmpty(timestamp)){
            throw new Fault(new IllegalArgumentException("timestamp can not be empty"));
        }

        if (!securityService.verify(timestamp,token)){
            throw new Fault(new IllegalArgumentException("token check fail"));
        }

    }

    private Map<String, String> getParamsMap(String strParams) {
        if (strParams == null || strParams.trim().length() <= 0) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        String[] params = strParams.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] arr = params[i].split("=");
            map.put(arr[0], arr[1]);
        }
        return map;
    }
}
