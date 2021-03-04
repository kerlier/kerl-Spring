package com.seckill.util;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiV2UserGetbymobileRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiV2UserGetbymobileResponse;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/2 17:29
 */
public class DingTalkVerifyUtil {
    private static final String ACCESS_TOKEN_QUERY_URL = "https://oapi.dingtalk.com/gettoken";

    private static final Long AGENT_ID = 1119634836L;

    private static final String APP_KEY = "dingokbqctll1syesj6x";

    private static final String APP_SECRET = "qM8ZrGcQbMllyQ2Wf1XHQsnnFB3NEtsC33Gr3qTjK5UbXWXZgAK0A-WaclnJ2lr4";

    private static final DateTimeFormatter FORMAT_NINETEEN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final Logger LOGGER = LoggerFactory.getLogger(DingTalkVerifyUtil.class);

    /**
     * 通过手机号发送工作通知
     * 1. 通过手机号获取userId
     * @param mobile
     * @param content
     */
    public static void sendWorkerNotice(String mobile,String content){
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(content)){
            LOGGER.error("mobile is {}, content: {}, one of args is empty",mobile,content);
            return;
        }

        try{
            String accessToken = getAccessToken();
            if(StringUtils.isEmpty(accessToken)){
                LOGGER.error("query token error: token is empty");
                return ;
            }

            String userId = getUserIdByMobile(accessToken, mobile);
            if(StringUtils.isEmpty(userId)){
                LOGGER.error("query userId by mobile {} error: userId is empty",mobile);
                return ;
            }

            //添加当前时间
            String dateStr = FORMAT_NINETEEN.format(LocalDateTime.now());

            content = content +"(当前时间："+dateStr+")";

            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
            OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
            request.setAgentId(AGENT_ID);
            request.setUseridList(userId);

            OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
            msg.setMsgtype("text");
            msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
            msg.getText().setContent(content);
            request.setMsg(msg);

            OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(request, accessToken);
            LOGGER.info("send message result: {}",rsp.getBody());
        }catch (Exception e){
            LOGGER.error("send message error",e);
        }
    }

    private static String getAccessToken() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient(ACCESS_TOKEN_QUERY_URL);
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(APP_KEY);
        request.setAppsecret(APP_SECRET);
        request.setHttpMethod("GET");
        OapiGettokenResponse response = client.execute(request);
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());

        String accessToken = Optional.ofNullable(jsonObject).map(x -> {
            return x.getString("access_token");
        }).orElse("");
        return accessToken;
    }

    private static String getUserIdByMobile(String accessToken,String mobile) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/getbymobile");
        OapiV2UserGetbymobileRequest req = new OapiV2UserGetbymobileRequest();
        req.setMobile(mobile);
        OapiV2UserGetbymobileResponse rsp = client.execute(req, accessToken);
        JSONObject jsonObject = JSONObject.parseObject(rsp.getBody());
        String userId = Optional.ofNullable(jsonObject)
                .map(x -> {
                            return JSONObject.parseObject(x.getString("result"));
                        }
                )
                .map(x -> {
                            return x.getString("userid");
                        }
                )
                .orElse("");
        return userId;
    }


    public static void main(String[] args) {
//        String str= "{\n" +
//                "    \"errcode\": 0,\n" +
//                "    \"access_token\": \"96fc7a7axxx\",\n" +
//                "    \"errmsg\": \"ok\",\n" +
//                "    \"expires_in\": 7200\n" +
//                "}";
//        String str1 = "";
//        JSONObject jsonObject = JSONObject.parseObject(str1);
//        String accessToken = Optional.ofNullable(jsonObject).map(x -> {
//            return x.getString("access_token");
//        }).orElse("");
//        System.out.println(accessToken);
//
//
//        System.out.println("===========");
//        String str2 = "";
//        JSONObject jsonObject1 = JSONObject.parseObject(str2);
//        String userId = Optional.ofNullable(jsonObject1)
//                .map(x -> {
//                            return JSONObject.parseObject(x.getString("result"));
//                        }
//                )
//                .map(x -> {
//                    return x.getString("userid");
//                })
//                .orElse("");
//        System.out.println("userId:"+ userId);

        sendWorkerNotice("18814665667","测试内容1");
    }
}
