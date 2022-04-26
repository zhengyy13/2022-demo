/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.design.design02.service.engine;

import com.d2y.demo.design.design02.model.aggregates.TreeRich;
import com.d2y.demo.design.design02.model.vo.EngineResult;

import java.util.Map;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-26 10:47
 */
public interface IEngine {
    EngineResult process(Long treeId, Long userId, TreeRich treeRich, Map<String, String> data);
}
