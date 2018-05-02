package com.shiep.service.service.impl;

import com.actionsoft.sdk.services.level0.IM;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.shiep.service.model.BaseResponse;
import com.shiep.service.model.SendMail;
import com.shiep.service.service.EmailService;
import com.shiep.service.util.InvokeUtil;

import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author louie
 * @date created in 2018-4-25 16:33
 */
@WebService(endpointInterface = "com.shiep.service.service.EmailService")
@Produces({MediaType.APPLICATION_JSON})
public class EmailServiceImpl implements EmailService {

    @POST
    @Path(value = "/send")
    @Override
    public String send(String data) {
        BaseResponse baseResponse = new BaseResponse(false,"send mail error");
        Integer result = 0;
        try {
            SendMail sendMail = JSON.parseObject(data, SendMail.class);
            String mailToCc = sendMail.getMailToCc();
            if (Strings.isNullOrEmpty(mailToCc)){
                result = Integer.valueOf(InvokeUtil.rmiInvoke(IM.class, "sendMail", new Object[]{
                        sendMail.getMailFrom(), sendMail.getMailTo(), sendMail.getSubject(), sendMail.getContent()
                }).toString());
            }else {
                result = Integer.valueOf(InvokeUtil.rmiInvoke(IM.class, "sendMail", new Object[]{
                        sendMail.getMailFrom(), sendMail.getMailTo(), sendMail.getMailToCc(), sendMail.getSubject(), sendMail.getContent()
                }).toString());
            }

            if (result > 0 ){
                baseResponse.setOk(true);
                baseResponse.setMessage(result+"");
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
            baseResponse.setMessage(e.getStackTrace()[0].getClassName()+":"+e.getMessage());
        }
        return JSON.toJSONString(baseResponse);
    }
}
