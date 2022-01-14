/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.ext.metadata;

import java.io.Serializable;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-01-14 10:45
 */
@EnableAsync
@Service("metaService")
public class MetaDemo extends HashMap<String, String> implements Serializable {
    @Autowired
    private String getName() {
        return "demo";
    }
}
