import com.efruit.ark.microsvr.oauth2.OAuth2Application;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 测试登录rest
 * 执行测试之前需要启动服务，否则会报错（I/O error on POST request for "http://localhost:8001/manage/role/list": Connection refused (Connection refused)）
 * Created by yangyang on 2018/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OAuth2Application.class)
@WebAppConfiguration
public class LoginController {

    final static String REMOTUEL = "http://39.105.9.165:8002";
//    final static String REMOTUEL = "http://localhost:8002";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaders headers;

    /**
     * 登录
     */
    @Test
    public void login(){
        try {
            String url = REMOTUEL + "/login/form";
            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
//            parm.add("username", "openid2");
            parm.add("username", "yangyang@efruitpro.com");
            parm.add("password", "111111");

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("本次登录结果为：" + response2.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

}
