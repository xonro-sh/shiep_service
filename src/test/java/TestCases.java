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
        String key = "KEY";
        StringBuilder resource = new StringBuilder();
        resource.append("key=").append(key).append("&timestamp=1525240885");
        System.out.println(DigestUtils.md5Hex(resource.toString()).toLowerCase());
    }

    @Test
    public void testTime(){
        System.out.println(System.currentTimeMillis()/1000);
    }
}
