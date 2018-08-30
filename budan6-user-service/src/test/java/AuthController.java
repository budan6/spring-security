import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.efruit.ark.microsvr.user.UserApplication;
import org.apache.http.util.EntityUtils;
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
 * 登录rest
 * 执行测试之前需要启动服务，否则会报错（I/O error on POST request for "http://localhost:8001/manage/role/list": Connection refused (Connection refused)）
 * Created by yangyang on 2018/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApplication.class)
@WebAppConfiguration
public class AuthController {

    final static String REMOTUEL = "http://localhost:8001";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpHeaders headers;

    /**
     * 根据登录账号获取用户信息
     */
    @Test
    public void auth(){
        try {
            String url = REMOTUEL+"/auth/login/user";
            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
            parm.add("loginName", "budan6");

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity reResult = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("根据用户名查询是否存在结果为：" + reResult.getBody());

            //获取用户的登录账号，用户密码，是否启用，角色名称，用户类型
            //请求成功
            if(reResult.getStatusCode().is2xxSuccessful()){
                JSONObject JSONObjectResult = JSON.parseObject(reResult.getBody().toString());
                //获取status
                String status = JSONObjectResult.get("status").toString();
                String msg = JSONObjectResult.get("msg").toString();
                //获取用户信息
                JSONObject jsonData = JSON.parseObject(JSONObjectResult.get("data").toString());
                JSONObject jsonDataUser = JSON.parseObject(jsonData.get("user").toString());
                String userid = jsonDataUser.get("userid").toString();
                String isenable = jsonDataUser.get("isenable").toString();
                String loginname = jsonDataUser.get("loginname").toString();
                String openid = jsonDataUser.get("openid").toString();
                String password = jsonDataUser.get("password").toString();
                String roleName = jsonDataUser.get("roleName").toString();
                String usertype = jsonDataUser.get("usertype").toString();

                //获取授权信息
                JSONArray JSONArrayPermissionList = JSON.parseArray(jsonData.get("permissionList").toString());
                String permissionid = null;
                String name = null;
                String permissionUrl = null;
                for (int i = 0 ;i< JSONArrayPermissionList.size();i++) {
                    JSONObject JSONObjectPermission = JSONArrayPermissionList.getJSONObject(i);
                    System.out.println(JSONObjectPermission.get("permissionid"));
                    System.out.println(JSONObjectPermission.get("name"));
                    System.out.println(JSONObjectPermission.get("url"));
                }

            }else{
                //请求失败
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据用户id查询所拥有的权限
     */
    @Test
    public void findPermissionByUserId(){
        try {
            String url = REMOTUEL+"/auth/login/findPermissionByUserId";

            MultiValueMap<String, Object> parm = new LinkedMultiValueMap<String, Object>();
            parm.add("userId", "0283ad3dc6fd41e5bb26b1fb7df77e9e");

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(parm,headers);
            ResponseEntity<String> response2 = restTemplate.postForEntity(url, httpEntity, String.class);
            System.out.println("根据用户id查询所拥有的权限结果为：" + response2.getBody());
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
