import com.alibaba.fastjson.JSON;
import com.shiep.service.model.SendMail;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @author louie
 * @date created in 2018-4-26 14:45
 */
public class TestCases {

    @Test
    public void getToken(){
        String key = "xonro-service-for-shiep";
        StringBuilder resource = new StringBuilder();
        resource.append("key=").append(key).append("&timestamp=timestamp");
        System.out.println(DigestUtils.md5Hex(resource.toString()));
    }

    @Test
    public void testToJson(){
        SendMail sendMail = new SendMail() {{
            setMailTo("admin");
            setMailToCc("aws-test");
            setSubject("this is test mail");
            setContent("hello,nice to meet you");
        }};
        System.out.println(JSON.toJSONString(sendMail));
    }
}
