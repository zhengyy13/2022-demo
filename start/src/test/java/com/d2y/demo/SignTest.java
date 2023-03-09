/*
 * Copyright (c) 2001-2023 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2023-02-10 11:22
 */
public class SignTest {

    @Test
    public void zhaoShangTest() {
        //1.先预添加signature内容，包含sigtim和sigdat两个KEY
        String requestText =
            "{\n" + "    \"request\": {\n" + "        \"head\": {\n" + "            \"funcode\": \"DCPAYOPR\",\n"
                + "            \"userid\": \"Y100023792\",\n"
                + "            \"reqid\": \"201901091123456780001fbdev01\"\n" + "        },\n" + "        \"body\": {\n"
                + "            \"modnbr\": \"000002\",\n" + "            \"refext\": \"2019012316273819\",\n"
                + "            \"sndeac\": \"110902773110203\",\n" + "            \"rcveac\": \"110902773110402\",\n"
                + "            \"rcvean\": \"北京全聚德\",\n" + "            \"trsamt\": \"100.99\"\n" + "        }\n"
                + "    },\n" + "    \"signature\": {\n" + "        \"sigtim\": \"20190823093102\",\n"
                + "        \"sigdat\": \"__signature_sigdat__\"\n" + "    }\n" + "}";
        JSONObject request = JSON.parseObject(requestText);
        System.out.println("原始请求："+JSON.toJSONString(request));

        //2.对上面请求报文的内容，对KEY按ASSIIC码排序后去掉报文字段中的空格和换行得到待签名字符串
        String sortRequest = JSON.toJSONString(request, SerializerFeature.MapSortField);
        System.out.println(sortRequest);

        //3.对上面待签名字符串用用户的RSA私钥进行签名，签名算法采用SHA256，对签名结果进行BASE64后获得签名结果。替换掉报文中sigdat的值

    }

    @Test
    public void zheShangTest() {
        BigDecimal ledgerTrsamt = new BigDecimal("1000");
        BigDecimal commissionRate = new BigDecimal("10");
        BigDecimal rate = commissionRate.divide(new BigDecimal("100"));
        System.out.println(ledgerTrsamt.multiply(rate));
        System.out.println(ledgerTrsamt.multiply(new BigDecimal("1").subtract(rate)));
    }
}
