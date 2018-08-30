import com.efruit.ark.microsvr.user.UserApplication;
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
 * 测试用户rest
 * 执行测试之前需要启动服务，否则会报错（I/O error on POST request for "http://localhost:8001/manage/user/list": Connection refused (Connection refused)）
 * Created by yangyang on 2018/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApplication.class)
@WebAppConfiguration
public class UserController {

    final static String REMOTUEL = "http://localhost:8001";

    final static String TOKEN = "Budan6 " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YW5neWFuZ0BlZnJ1aXRwcm8uY29tIiwiYXVkIjoiYXJrIiwiYXV0aFVybCI6W3siYXV0aG9yaXR5IjoiL21hbmFnZS8qLyoifV0sImNyZWF0ZWQiOjE1MzUzNTA1MDcyNjksImlzcyI6IjAyMjk1NDg2YmIyZTQzM2JiNmY3N2IyN2FkM2U0MGY0IiwiZXhwIjoxNTQxMzUwNTA3MjY5LCJpYXQiOjE1MzUzNTA1MDcyNjl9.W9iGrvpR_XmPKhrauWodxbQw4TFmiqiznCI1pW3xktI";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaders headers;

    /**
     * 获取用户列表
     */
    @Test
    public void list(){
        try {
            String url = REMOTUEL+"/manage/user/list";
            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
//            parm.add("wechatid", "budan6");

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", TOKEN);
            //parm.add("loginname", "loginname");
            //parm.add("sex", "男");
            //parm.add("isenable", "启用");
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("获取用户列表结果为：" + response2.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 新增用户
     */
    @Test
    public void insert(){
        try {
            String url = REMOTUEL+"/manage/user/insert";

            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
            parm.add("roleid", "52ca696c37a8403292f744f89781dae6");
            parm.add("nickname", "budan");
            parm.add("loginname", "Budan6");
            parm.add("password", "111111");
            parm.add("wechatid", "budan3");
            parm.add("wechatheadimg", "headimags");
            parm.add("openid", "openi554");
            parm.add("sex", "男");
            parm.add("phone", "11022");
            parm.add("region", "中国北京");
            parm.add("isenable", "启用");
            parm.add("usertype", "manage");


            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", TOKEN);
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("新增用户结果为：" + response2.getBody());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改用户状态
     */
    @Test
    public void update(){
        try {
            String url = REMOTUEL+"/manage/user/update";

            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
            parm.add("userid", "0283ad3dc6fd41e5bb26b1fb7df77e9e");
            parm.add("isenable", "启用");
            //parm.add("roleid", "15288b3baae54ca29af5c78a1dfefdc3");
            parm.add("phone", "111");


            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("修改用户状态结果为：" + response2.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 检查用户名是否存在（新增前）
     */
    @Test
    public void checkLoginname(){
        try {
            String url = REMOTUEL+"/manage/user/checkLoginname";

            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
            parm.add("usertype", "manage");
            parm.add("loginname", "Budan6");


            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("检查用户名是否存在（新增前）结果为：" + response2.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据用户id获取用户详细信息
     */
    @Test
    public void getDetail(){
        try {
            String url = REMOTUEL+"/manage/user/getDetail";

            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();


            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", TOKEN);
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("根据用户id获取用户详细信息结果为：" + response2.getBody());
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
