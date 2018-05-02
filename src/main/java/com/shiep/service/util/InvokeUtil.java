package com.shiep.service.util;

import com.actionsoft.awf.services.WSDKException;
import com.actionsoft.loadbalancer.client.AWSBalancer;
import com.actionsoft.loadbalancer.client.cluster.HostModel;
import com.actionsoft.sdk.local.WsRMIMessageModel;
import com.actionsoft.webframework.constant.Constant;
import gnu.cajo.invoke.Remote;
import gnu.cajo.invoke.Remote_Stub;
import org.apache.log4j.Logger;

import javax.xml.ws.WebServiceContext;
import java.rmi.ConnectException;

/**
 * Description: invoke
 *
 * @author lazy
 * @date 2016-4-16
 */
public class InvokeUtil {

    private static Logger logger = Logger.getLogger(InvokeUtil.class);

    private static Object invokeInner(WebServiceContext context, Class<?> cls, String method, Object[] params, int i) throws Exception {
        HostModel host = AWSBalancer.getInstance().getHostByHttpRequest();
        if (host == null) {
            throw new Exception(Constant.consoleInfo() + "没有可用的AWS服务节点");
        }

        try {
            String url = "//" + host.getHostIp() + ":" + host.getRmiPort() + "/WebServiceProxy";
            WsRMIMessageModel mm = new WsRMIMessageModel();
            mm.setLang("cn");
            mm.setOpkey("http://services.sdk.actionsoft.com/");
            mm.setSecId("d0986221d458311363bcb36c8a57496e");

            mm.setSvType(cls.getSimpleName());
            mm.setSvMethod(method);
            mm.setParams(params);
            Remote_Stub client = (Remote_Stub) Remote.getItem(url);
            return client.invoke("invoke", new Object[]{mm});
        } catch (ConnectException e) {
            if ((AWSBalancer.isServices) && (AWSBalancer.usableHosts != null)
                    && (i < AWSBalancer.usableHosts.size())) {
                AWSBalancer.getInstance().abandonedHost(host);
                return rmiInvoke(null, method, params);
            }
            throw new Exception(Constant.consoleInfo() + "没有可用的AWS服务节点");
        } catch (Exception e) {
            throw e;
        }
    }

    public static Object rmiInvoke(Class<?> cls, String method, Object[] params) throws WSDKException {
        return rmiInvoke(null, cls, method, params);
    }

    public static Object rmiInvoke(WebServiceContext context, Class<?> cls, String method, Object[] params) throws WSDKException {
        try {
            return invokeInner(context, cls, method, params, 0);
        } catch (Exception e) {
            logger.error(e);
            throw new WSDKException(e);
        }
    }
}
