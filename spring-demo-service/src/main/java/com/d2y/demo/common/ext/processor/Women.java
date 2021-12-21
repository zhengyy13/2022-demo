/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.ext.processor;

import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-20 17:48
 */
@Service("women")
public class Women implements PeopleService {
    @Override
    public void introduce() {
        System.out.println("I am woman");
    }
}
