/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.listener;

import com.d2y.demo.mybatis.entity.MybatisDemoUser;

import java.util.concurrent.locks.LockSupport;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-14 10:50
 */
@Component
public class EmailEventListener implements ApplicationListener<EmailEvent> {

    @Override
    @Async("defaultTaskExecutor")
    public void onApplicationEvent(EmailEvent event) {
        System.out.println("事件处理(implements ApplicationListener)："+ Thread.currentThread().getName());
        MybatisDemoUser mybatisDemoUser = (MybatisDemoUser) event.getSource();
        System.out.println("Email事件处理中(implements ApplicationListener)：" + mybatisDemoUser.toString());
        LockSupport.parkNanos(1000 * 1000 * 2000L);
    }
}
