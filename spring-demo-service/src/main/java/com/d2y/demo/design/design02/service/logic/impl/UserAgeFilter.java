/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.design.design02.service.logic.impl;

import com.d2y.demo.design.design02.service.logic.BaseLogic;

import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-26 10:40
 */
@Component
public class UserAgeFilter extends BaseLogic {
    @Override
    public String getPendingDecisionValue(Long treeId, Long userId, Map<String, String> data) {
        return data.get("age");
    }
}
