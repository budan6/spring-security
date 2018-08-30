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
 * 测试权限rest
 * 执行测试之前需要启动服务，否则会报错（I/O error on POST request for "http://localhost:8001/manage/role/list": Connection refused (Connection refused)）
 * Created by yangyang on 2018/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApplication.class)
@WebAppConfiguration
public class PermissionController {

    final static String REMOTUEL = "http://localhost:8001/manage/permission";


    final static String TOKEN = "Budan6 " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YW5neWFuZ0BlZnJ1aXRwcm8uY29tIiwiYXVkIjoiYXJrIiwiYXV0aFVybCI6W3siYXV0aG9yaXR5IjoiL21hbmFnZS8qLyoifV0sImNyZWF0ZWQiOjE1MzUwODUyODk4NjMsImlzcyI6ImNyZWF0ZWQiLCJleHAiOjE1MzUwODU4ODk4NjMsImlhdCI6MTUzNTA4NTI4OTg2M30.NDf5tjiOVLz-HK4jGexLukpR0P_1aQ7xtGCYm7rMfsY";


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaders headers;

    /**
     * 获取列表
     */
    @Test
    public void list(){
        try {
            String url = REMOTUEL+"/list";
            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
//            parm.add("roleid", "15288b3baae54ca29af5c78a1dfefdc3");

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", TOKEN);
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("获取列表结果为：" + response2.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增
     */
    @Test
    public void insert(){
        try {
            String url = REMOTUEL+"/insert";

            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
            parm.add("name", "用户管理");
            parm.add("descritpion", "新增/修改/列表/检查用户是否存在");
            parm.add("url", "/manage/role/*");
            parm.add("roleid", "52ca696c37a8403292f744f89781dae6");
            parm.add("pid", "");

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("新增结果为：" + response2.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改
     */
    @Test
    public void update(){
        try {
            String url = REMOTUEL+  "/update";

            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
            parm.add("permissionid", "611fe30bc5074120bf93151ffd109da6");
            parm.add("name", "管理");
            parm.add("descritpion", "后台管理所有功能");
            parm.add("url", "/manage/*/*");
            parm.add("roleid", "52ca696c37a8403292f744f89781dae6");
            parm.add("pid", "");


            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("修改结果为：" + response2.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除
     */
    @Test
    public void delete(){
        try {
            String url = REMOTUEL+  "/delete";

            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
            parm.add("permissionid", "63205476d7a2450687c52bf5e59b61eb");


            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("删除结果为：" + response2.getBody());
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
