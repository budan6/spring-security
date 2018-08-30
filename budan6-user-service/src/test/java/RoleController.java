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
 * 测试角色rest
 * 执行测试之前需要启动服务，否则会报错（I/O error on POST request for "http://localhost:8001/manage/role/list": Connection refused (Connection refused)）
 * Created by yangyang on 2018/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApplication.class)
@WebAppConfiguration
public class RoleController {

    final static String REMOTUEL = "http://localhost:8001";
//
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaders headers;

    /**
     * 获取角色列表
     */
    @Test
    public void list(){
        try {
            String url = REMOTUEL+"/manage/role/list";
            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
            //parm.add("rolename", "rolename");
            parm.add("isenable", "禁用");

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("获取角色列表结果为：" + response2.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新增角色
     */
    @Test
    public void insert(){
        try {
            String url = REMOTUEL+"/manage/role/insert";

            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
            parm.add("rolename", "超级管理员");
            parm.add("isenable", "启用");

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("新增角色结果为：" + response2.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改角色
     */
    @Test
    public void update(){
        try {
            String url = REMOTUEL +"/manage/role/update";

            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
            parm.add("rolename", "ROLE_ADMIN");
            parm.add("isenable", "启用22");
            parm.add("roleid", "cde3ace70fc243b8b378ee8ac397f096");


            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("修改角色结果为：" + response2.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 检查角色名是否存在（新增前）
     */
    @Test
    public void checkRolename(){
        try {
            String url = REMOTUEL+"/manage/role/checkRolename";

            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
            parm.add("rolename", "ROLE_ADMIN");


            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("检查角色名是否存在（新增前）结果为：" + response2.getBody());
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
