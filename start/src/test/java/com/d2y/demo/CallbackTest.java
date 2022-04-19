/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo;

import com.d2y.demo.common.Invoke.CallbackRequest;
import com.d2y.demo.common.Invoke.CallbackRespose;

import java.util.concurrent.locks.LockSupport;

import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-19 17:01
 */
public class CallbackTest {
    @Test
    public void test() {
        CallbackRespose callbackRespose = new CallbackRespose();
        CallbackRequest callbackRequest = new CallbackRequest(callbackRespose);
        callbackRequest.request("1+1=?");
        LockSupport.parkNanos(5L * 1000 * 1000 * 1000);
    }
}
