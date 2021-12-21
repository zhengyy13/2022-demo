/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.listener;

import org.springframework.context.ApplicationEvent;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-14 10:42
 */
public class EmailEvent<MybatisDemoUser> extends ApplicationEvent {
    public EmailEvent(MybatisDemoUser source) {
        super(source);
    }
}
