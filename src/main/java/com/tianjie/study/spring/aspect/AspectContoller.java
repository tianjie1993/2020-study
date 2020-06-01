package com.tianjie.study.spring.aspect;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @version v1.0
 * @description TODO
 * @auth tian.jie
 * @date {date} {time}
 */
@RestController
public class AspectContoller {


    @GetMapping("aspectName")
    public String getname(){
        System.out.println(this.getClass().getName());
        return "a";
    }

    @GetMapping("getData")
    public String getData(){
        return "{\"state\":true,\"pageNo\":1,\"totalPages\":1,\"pageSize\":20,\"total\":11,\"isHavePrePage\":false,\"isHaveNextPage\":false,\"customArgs\":\"\",\"rows\":[{\"ID\":\"13a11a0f-3cf8-4432-af40-54a47ce73317\",\"CREATEDATE\":\"2018-06-03 00:22:00:000\",\"CREATEUSER\":\"admin\",\"UPDATEDATE\":\"2018-06-03 00:22:00:000\",\"UPDATEUSER\":\"admin\",\"CODE\":\"3307\",\"NAME\":\"金华市\",\"PROVINCE_CODE\":\"33\",\"PROVINCE_NAME\":\"浙江省\",\"DATA_TYPE\":\"2\",\"id\":\"13a11a0f-3cf8-4432-af40-54a47ce73317\"},{\"ID\":\"175dc990-a431-4809-afc8-332c1a433c8e\",\"CREATEDATE\":\"2018-06-03 00:21:58:000\",\"CREATEUSER\":\"admin\",\"UPDATEDATE\":\"2018-06-03 00:21:58:000\",\"UPDATEUSER\":\"admin\",\"CODE\":\"3301\",\"NAME\":\"杭州市\",\"PROVINCE_CODE\":\"33\",\"PROVINCE_NAME\":\"浙江省\",\"DATA_TYPE\":\"2\",\"id\":\"175dc990-a431-4809-afc8-332c1a433c8e\"},{\"ID\":\"29f9dc1c-00a5-4516-bca5-cd9fd9a65bb2\",\"CREATEDATE\":\"2018-06-03 00:21:58:000\",\"CREATEUSER\":\"admin\",\"UPDATEDATE\":\"2018-06-03 00:21:58:000\",\"UPDATEUSER\":\"admin\",\"CODE\":\"3311\",\"NAME\":\"丽水市\",\"PROVINCE_CODE\":\"33\",\"PROVINCE_NAME\":\"浙江省\",\"DATA_TYPE\":\"2\",\"id\":\"29f9dc1c-00a5-4516-bca5-cd9fd9a65bb2\"},{\"ID\":\"52ff3dc4-1c35-41a0-9ac8-0ac1ac9a0a92\",\"CREATEDATE\":\"2018-06-03 00:21:57:000\",\"CREATEUSER\":\"admin\",\"UPDATEDATE\":\"2018-06-03 00:21:57:000\",\"UPDATEUSER\":\"admin\",\"CODE\":\"3310\",\"NAME\":\"台州市\",\"PROVINCE_CODE\":\"33\",\"PROVINCE_NAME\":\"浙江省\",\"DATA_TYPE\":\"2\",\"id\":\"52ff3dc4-1c35-41a0-9ac8-0ac1ac9a0a92\"},{\"ID\":\"5ca3a52e-fd09-4f61-bff9-c5d00f322bc6\",\"CREATEDATE\":\"2018-06-03 00:21:54:000\",\"CREATEUSER\":\"admin\",\"UPDATEDATE\":\"2018-06-03 00:21:54:000\",\"UPDATEUSER\":\"admin\",\"CODE\":\"3303\",\"NAME\":\"温州市\",\"PROVINCE_CODE\":\"33\",\"PROVINCE_NAME\":\"浙江省\",\"DATA_TYPE\":\"2\",\"id\":\"5ca3a52e-fd09-4f61-bff9-c5d00f322bc6\"},{\"ID\":\"72f486ec-b823-4e24-9935-ee4546ddde9e\",\"CREATEDATE\":\"2018-06-03 00:21:56:000\",\"CREATEUSER\":\"admin\",\"UPDATEDATE\":\"2018-06-03 00:21:56:000\",\"UPDATEUSER\":\"admin\",\"CODE\":\"3306\",\"NAME\":\"绍兴市\",\"PROVINCE_CODE\":\"33\",\"PROVINCE_NAME\":\"浙江省\",\"DATA_TYPE\":\"2\",\"id\":\"72f486ec-b823-4e24-9935-ee4546ddde9e\"},{\"ID\":\"8be9c5da-64b4-4ea8-8c90-efe42e614bb6\",\"CREATEDATE\":\"2018-06-03 00:21:53:000\",\"CREATEUSER\":\"admin\",\"UPDATEDATE\":\"2018-06-03 00:21:53:000\",\"UPDATEUSER\":\"admin\",\"CODE\":\"3308\",\"NAME\":\"衢州市\",\"PROVINCE_CODE\":\"33\",\"PROVINCE_NAME\":\"浙江省\",\"DATA_TYPE\":\"2\",\"id\":\"8be9c5da-64b4-4ea8-8c90-efe42e614bb6\"},{\"ID\":\"b20e3304-6fef-42d4-8811-6ff733758dfd\",\"CREATEDATE\":\"2018-06-03 00:22:00:000\",\"CREATEUSER\":\"admin\",\"UPDATEDATE\":\"2018-06-03 00:22:00:000\",\"UPDATEUSER\":\"admin\",\"CODE\":\"3309\",\"NAME\":\"舟山市\",\"PROVINCE_CODE\":\"33\",\"PROVINCE_NAME\":\"浙江省\",\"DATA_TYPE\":\"2\",\"id\":\"b20e3304-6fef-42d4-8811-6ff733758dfd\"},{\"ID\":\"bef9c9d1-f695-45ed-b0fe-d4c272b3f265\",\"CREATEDATE\":\"2018-06-03 00:21:53:000\",\"CREATEUSER\":\"admin\",\"UPDATEDATE\":\"2018-06-03 00:21:53:000\",\"UPDATEUSER\":\"admin\",\"CODE\":\"3304\",\"NAME\":\"嘉兴市\",\"PROVINCE_CODE\":\"33\",\"PROVINCE_NAME\":\"浙江省\",\"DATA_TYPE\":\"2\",\"id\":\"bef9c9d1-f695-45ed-b0fe-d4c272b3f265\"},{\"ID\":\"d8a20941-1928-4e9e-b092-7d1996acb118\",\"CREATEDATE\":\"2018-06-03 00:22:00:000\",\"CREATEUSER\":\"admin\",\"UPDATEDATE\":\"2018-06-03 00:22:00:000\",\"UPDATEUSER\":\"admin\",\"CODE\":\"3302\",\"NAME\":\"宁波市\",\"PROVINCE_CODE\":\"33\",\"PROVINCE_NAME\":\"浙江省\",\"DATA_TYPE\":\"2\",\"id\":\"d8a20941-1928-4e9e-b092-7d1996acb118\"},{\"ID\":\"f5bd265d-ee86-4add-a029-a910e2843a32\",\"CREATEDATE\":\"2018-06-03 00:21:55:000\",\"CREATEUSER\":\"admin\",\"UPDATEDATE\":\"2018-06-03 00:21:55:000\",\"UPDATEUSER\":\"admin\",\"CODE\":\"3305\",\"NAME\":\"湖州市\",\"PROVINCE_CODE\":\"33\",\"PROVINCE_NAME\":\"浙江省\",\"DATA_TYPE\":\"2\",\"id\":\"f5bd265d-ee86-4add-a029-a910e2843a32\"}]}";
    }

    @PostMapping("nxsoprice")
    public String getData1(@RequestBody List<NxsopriceDto> dtos){
        System.out.println(dtos);
        return "{\n" +
                "    \"isSuccess\": true,\n" +
                "    \"remark\": null,\n" +
                "    \"code\": 1,\n" +
                "    \"message\": \"\",\n" +
                "    \"messageid\": null,\n" +
                "    \"versiontime\": null,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"itemcode\": \"90.YG.2033.DS000005\",\n" +
                "            \"businessdate\": \"2020-01-05T00:00:00\",\n" +
                "            \"customercode\": \"FO-880001\",\n" +
                "            \"mfg\": \"ZF001\",\n" +
                "            \"discountrate\": 1.000000000,\n" +
                "            \"price\": 2530.000000000,\n" +
                "            \"finalprice\": 2530.000000000000000000,\n" +
                "            \"componentline\": [\n" +
                "                {\n" +
                "                    \"itemcode\": \"90.YG.2033.3002.DS000002\",\n" +
                "                    \"preitemcode\": \"\",\n" +
                "                    \"usageqty\": 1.0,\n" +
                "                    \"discountrate\": 1.000000000,\n" +
                "                    \"price\": 1600.000000000,\n" +
                "                    \"finalprice\": 1600.000000000000000000\n" +
                "                },\n" +
                "                {\n" +
                "                    \"itemcode\": \"90.YG.2033.1053.DS000002\",\n" +
                "                    \"preitemcode\": \"\",\n" +
                "                    \"usageqty\": 1.0,\n" +
                "                    \"discountrate\": 1.000000000,\n" +
                "                    \"price\": 930.000000000,\n" +
                "                    \"finalprice\": 930.000000000000000000\n" +
                "                }\n" +
                "            ],\n" +
                "            \"code\": 1,\n" +
                "            \"message\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemcode\": \"90.9001.C000003\",\n" +
                "            \"businessdate\": \"2020-01-05T00:00:00\",\n" +
                "            \"customercode\": \"FO-880001\",\n" +
                "            \"mfg\": \"T2M~F2F\",\n" +
                "            \"discountrate\": 1.000000000,\n" +
                "            \"price\": 6786.000000000,\n" +
                "            \"finalprice\": 6786.000000000000000000,\n" +
                "            \"componentline\": [\n" +
                "                {\n" +
                "                    \"itemcode\": \"90.9001.1011.C000002\",\n" +
                "                    \"preitemcode\": \"90.9001.1011\",\n" +
                "                    \"usageqty\": 1.0,\n" +
                "                    \"discountrate\": 1.000000000,\n" +
                "                    \"price\": 1248.000000000,\n" +
                "                    \"finalprice\": 1248.000000000000000000\n" +
                "                },\n" +
                "                {\n" +
                "                    \"itemcode\": \"90.9001.3033.C000003\",\n" +
                "                    \"preitemcode\": \"90.9001.3033\",\n" +
                "                    \"usageqty\": 1.0,\n" +
                "                    \"discountrate\": 1.000000000,\n" +
                "                    \"price\": 3069.000000000,\n" +
                "                    \"finalprice\": 3069.000000000000000000\n" +
                "                },\n" +
                "                {\n" +
                "                    \"itemcode\": \"90.9001.5042.C000003\",\n" +
                "                    \"preitemcode\": \"90.9001.5042\",\n" +
                "                    \"usageqty\": 1.0,\n" +
                "                    \"discountrate\": 1.000000000,\n" +
                "                    \"price\": 2469.000000000,\n" +
                "                    \"finalprice\": 2469.000000000000000000\n" +
                "                }\n" +
                "            ],\n" +
                "            \"code\": 1,\n" +
                "            \"message\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"itemcode\": \"91.A5906.01\",\n" +
                "            \"businessdate\": \"2020-01-05T00:00:00\",\n" +
                "            \"customercode\": \"FO-880001\",\n" +
                "            \"mfg\": \"ZF001\",\n" +
                "            \"discountrate\": 1.000000000,\n" +
                "            \"price\": 550.000000000,\n" +
                "            \"finalprice\": 550.000000000000000000,\n" +
                "            \"componentline\": [],\n" +
                "            \"code\": 1,\n" +
                "            \"message\": null\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}
