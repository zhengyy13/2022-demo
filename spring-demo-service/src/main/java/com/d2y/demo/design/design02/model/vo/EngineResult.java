/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.design.design02.model.vo;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-26 10:11
 */
@Data
public class EngineResult {
    private Long treeId;

    private Long userId;

    private Long nodeId;

    private String nodeValue;

    public EngineResult(Long treeId, Long userId, Long nodeId, String nodeValue) {
        this.treeId = treeId;
        this.userId = userId;
        this.nodeId = nodeId;
        this.nodeValue = nodeValue;
    }
}
