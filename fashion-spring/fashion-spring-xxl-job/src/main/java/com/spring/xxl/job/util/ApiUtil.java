package com.spring.xxl.job.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @Author: yangyuguang
 * @Date: 2021/6/24 16:25
 */
public class ApiUtil {

    private static String cookie="";

    /**
     * 新增/编辑任务
     * @param url
     * @param requestInfo
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static JSONObject addJob(String url, JSONObject requestInfo) throws HttpException, IOException {
        String path = "jobinfo/add";
        String targetUrl = url + path;
        HttpClient httpClient = new HttpClient();
        PostMethod post = new PostMethod(targetUrl);
        NameValuePair[] nameValuePairs = new NameValuePair[requestInfo.size()];
        Set<Map.Entry<String, Object>> entries = requestInfo.entrySet();
        int i = 0;
        for (Map.Entry<String, Object> entry : entries) {
            NameValuePair nameValuePair = new NameValuePair(entry.getKey(),String.valueOf(entry.getValue()));
            nameValuePairs[i] = nameValuePair;
            i++;
        }
        post.setRequestBody(nameValuePairs);
//        RequestEntity requestEntity = new StringRequestEntity(requestInfo.toString(),
//                "application/json", "utf-8");
        post.setRequestHeader("XXL_JOB_LOGIN_IDENTITY", cookie);
        post.setRequestHeader("cookie",cookie);
        httpClient.executeMethod(post);
        JSONObject result = new JSONObject();
        result = getJsonObject(post, result);
        return result;
    }

    /**
     * 删除任务
     * @param url
     * @param id
     * @return
     * @throws IOException
     */
    public static JSONObject deleteJob(String url,int id) throws HttpException, IOException {
        String path = "/api/jobinfo/delete?id="+id;
        return doGet(url,path);
    }

    /**
     * 开始任务
     * @param url
     * @param id
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static JSONObject startJob(String url,int id) throws HttpException, IOException {
        String path = "/api/jobinfo/start?id="+id;
        return doGet(url,path);
    }

    /**
     * 停止任务
     * @param url
     * @param id
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public static JSONObject stopJob(String url,int id) throws HttpException, IOException {
        String path = "/api/jobinfo/stop?id="+id;
        return doGet(url,path);
    }

    public static JSONObject doGet(String url,String path) throws HttpException, IOException {
        String targetUrl = url + path;
        HttpClient httpClient = new HttpClient();
        HttpMethod get = new GetMethod(targetUrl);
        get.setRequestHeader("cookie", cookie);
        httpClient.executeMethod(get);
        JSONObject result = new JSONObject();
        result = getJsonObject(get, result);
        return result;
    }

    private static JSONObject getJsonObject(HttpMethod get, JSONObject result) throws IOException {
        if (get.getStatusCode() == 200) {
            String responseBodyAsString = get.getResponseBodyAsString();
            System.out.println("执行结果: " + responseBodyAsString);
            result = JSONObject.parseObject(responseBodyAsString);
        } else {
            try {
                result = JSONObject.parseObject(get.getResponseBodyAsString());
                System.out.println("执行结果: " + result);
            } catch (Exception e) {
                result.put("error", get.getResponseBodyAsString());
            }
        }
        return result;
    }


    public static String login(String url, String userName, String password) throws HttpException, IOException {
        String path = "/login?userName="+userName+"&password="+password;
        String targetUrl = url + path;
        HttpClient httpClient = new HttpClient();
        PostMethod get = new PostMethod(targetUrl);
        httpClient.executeMethod(get);
        if (get.getStatusCode() == 200) {
            Cookie[] cookies = httpClient.getState().getCookies();
            StringBuffer tmpcookies = new StringBuffer();
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString() + ";");
            }
            cookie = tmpcookies.toString();
        } else {
            try {
                cookie = "";
            } catch (Exception e) {
                cookie="";
            }
        }
        return cookie;
    }

    public static void main(String[] args) throws IOException {

        String admin = login("http://localhost:8080/xxl-job-admin", "admin", "123456");
        JSONObject requestInfo = new JSONObject();
        requestInfo.put("jobGroup",1);
        requestInfo.put("jobDesc","1234");
        requestInfo.put("executorRouteStrategy","FIRST");
        requestInfo.put("cronGen_display","0 0/1 * * * ?");
        requestInfo.put("jobCron","0 0/1 * * * ?");
        requestInfo.put("misfireStrategy","DO_NOTHING");
        requestInfo.put("glueType","BEAN");
        requestInfo.put("executorHandler","demoJobHandler");
        requestInfo.put("executorBlockStrategy","SERIAL_EXECUTION");
        requestInfo.put("executorTimeout",0);
        requestInfo.put("executorFailRetryCount",1);
        requestInfo.put("author","admin");
        requestInfo.put("alarmEmail","xxx@qq.com");
        requestInfo.put("executorParam","att");
        //scheduleType
        requestInfo.put("scheduleType","NONE");
        requestInfo.put("glueRemark","GLUE代码初始化");
        JSONObject response=ApiUtil.addJob("http://localhost:8080/xxl-job-admin/",requestInfo);
        if(response.containsKey("code")&&200==response.getInteger("code")){
            System.out.println("新建成功");
        }else{
            System.out.println("新建失败");
        }
    }
}
