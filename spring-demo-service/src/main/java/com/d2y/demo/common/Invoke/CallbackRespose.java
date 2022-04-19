/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.Invoke;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-19 16:54
 */
public class CallbackRespose {
    public void handle(Callback callback, String request) {
        System.out.println(callback.getClass() + "问的问题是:" + request);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = "答案：" + request;
        callback.solve(result);
    }
}
