/*
 * Copyright (c) 2001-2023 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.functionalinterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2023-03-09 14:11
 */
public class FunctionTest {

    public static final String text = "{\n" + "    \"date\": \"20230309133908\",\n" + "    \"mobileSerial\": \"\",\n"
        + "    \"mobileCode\": \"\",\n" + "    \"method\": \"confirmPay\",\n"
        + "    \"tradeNo\": \"PD202303091150062\",\n" + "    \"mobile\": \"\",\n" + "    \"version\": \"2_0\",\n"
        + "    \"receiptSerialNo\": \"REC20220816000000727\",\n" + "    \"receiptAmount\": \"0.78\",\n"
        + "    \"totalAmount\": \"1.00\",\n" + "    \"payAmount\": \"1.00\",\n" + "    \"merId\": \"195265895\"\n" + "}";

    @Test
    public void test() {
        ZFunction<String, JSONObject> zFunction = x -> JSON.parseObject(x);
        System.out.println(zFunction.apply(text));
    }

    @Test
    public void compose() {
        ZFunction<Integer, Integer> zFunction = x -> x * x;
        ZFunction<Integer, Integer> zFunction1 = x -> x + x;
        ZFunction<Integer, Integer> compose = zFunction.compose(zFunction1);
        System.out.println(compose.apply(3));
    }

    @Test
    public void andThen() {
        ZFunction<Integer, Integer> zFunction = x -> x * x;
        ZFunction<Integer, Integer> zFunction1 = x -> x + x;
        ZFunction<Integer, Integer> andThen = zFunction.andThen(zFunction1);
        System.out.println(andThen.apply(3));
    }

    @Test
    public void identity() {
        System.out.println(ZFunction.identity().apply("xxxxxxxxx"));
    }
}
