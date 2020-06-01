package com.tianjie.study;

import com.actionsoft.bpms.api.OpenApiClient;
import com.actionsoft.sdk.service.response.BoolResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
@SpringBootTest
public class AwsApi {

    @Test
    public void testCheck(){
        String apiServer = "http://47.99.166.199/portal/openapi";
        String apiMethod = "storeInfo.checkProcess";
        String accessKey = "f11f41a5-28a0-45ec-99c5-1b5bbe1dcf33";
        String secret = "b4465299-8bed-4ec0-818b-e22b60402egf";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("CHECK_CODE", "80017233");
        //param.put("CHECK_CODE", "80017247");
        param.put("CHECK_TYPE", "store");
        OpenApiClient client = new OpenApiClient(apiServer, accessKey, secret);
        BoolResponse r = client.exec(apiMethod, param, BoolResponse.class);
        System.out.println(r.isData());
        System.out.println(r.getMsg());
    }
}
