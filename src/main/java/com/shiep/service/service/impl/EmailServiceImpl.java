package com.shiep.service.service.impl;

import com.actionsoft.sdk.services.level0.IM;
import com.alibaba.fastjson.JSON;
import com.shiep.service.model.BaseResponse;
import com.shiep.service.model.SendMail;
import com.shiep.service.service.EmailService;
import com.shiep.service.util.InvokeUtil;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceContext;

/**
 * @author louie
 * @date created in 2018-4-25 16:33
 */
@Produces({MediaType.APPLICATION_JSON})
public class EmailServiceImpl implements EmailService {
    @Resource
    private WebServiceContext context;

    @POST
    @Path(value = "/send")
    @Override
    public String send(String data) {
        SendMail sendMail = JSON.parseObject(data, SendMail.class);
        BaseResponse baseResponse = new BaseResponse(true,"");
        try {
            String result = InvokeUtil.rmiInvoke(IM.class,"sendMail",new Object[]{
                    sendMail.getMailFrom(),sendMail.getMailTo(),sendMail.getMailToCc(),sendMail.getSubject(),sendMail.getContent()
            }).toString();
            baseResponse.setMessage(result);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            baseResponse.setOk(false);
            baseResponse.setMessage(e.getMessage());
        }
        return JSON.toJSONString(baseResponse);
    }
}
