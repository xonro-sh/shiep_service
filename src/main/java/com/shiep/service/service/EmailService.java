package com.shiep.service.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;

/**
 * 邮件服务接口
 *
 * @author louie
 * @date created in 2018-4-25 16:31
 */
@WebService
@Produces({MediaType.APPLICATION_JSON})
public interface EmailService extends Serializable{
    String send(String data);
}
