/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.Invoke;

import java.util.concurrent.CompletableFuture;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-19 16:54
 */
public class CallbackRequest implements Callback {
    private CallbackRespose callbackRespose;

    public CallbackRequest(CallbackRespose callbackRespose) {
        this.callbackRespose = callbackRespose;
    }

    public void request(String question) {
        System.out.println("CallbackRequest发起了提问");
        CompletableFuture.runAsync(() -> {
            this.callbackRespose.handle(this, question);
        });
        doOtherthing();
    }

    private void doOtherthing() {
        System.out.println("先去做自己的其他事情");
    }

    @Override
    public void solve(String result) {
        System.out.println("回调函数返回的结果：" + result);
    }
}
