/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.design.design02.service.logic;

import com.d2y.demo.design.design02.model.vo.TreeNodeLink;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-26 10:16
 */
public interface LogicFilter {

    /**
     * 获取待决策值
     *
     * @param treeId
     * @param userId
     * @param data
     * @return
     */
    String getPendingDecisionValue(Long treeId, Long userId, Map<String, String> data);

    /**
     * 决策
     *
     * @param pendingDecisionValue
     * @param treeNodeLinkList
     * @return
     */
    Long filter(String pendingDecisionValue, List<TreeNodeLink> treeNodeLinkList);
}
