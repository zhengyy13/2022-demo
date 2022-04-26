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
 * @since 2022-04-26 10:22
 */
public abstract class BaseLogic implements LogicFilter {

    public abstract String getPendingDecisionValue(Long treeId, Long userId, Map<String, String> data);

    @Override
    public Long filter(String pendingDecisionValue, List<TreeNodeLink> treeNodeLinkList) {
        for (TreeNodeLink treeNodeLink : treeNodeLinkList) {
            if (decision(pendingDecisionValue, treeNodeLink)) {
                return treeNodeLink.getNodeIdTo();
            }
        }
        return 0L;
    }

    boolean decision(String pendingDecisionValue, TreeNodeLink treeNodeLink) {
        switch (treeNodeLink.getRuleLimitType()) {
        //1:=;2:>;3:<;4:>=;5<=;
        case 1:
            return pendingDecisionValue.equals(treeNodeLink.getRuleLimitValue());
        case 2:
            return Double.parseDouble(pendingDecisionValue) > Double.parseDouble(treeNodeLink.getRuleLimitValue());
        case 3:
            return Double.parseDouble(pendingDecisionValue) < Double.parseDouble(treeNodeLink.getRuleLimitValue());
        case 4:
            return Double.parseDouble(pendingDecisionValue) <= Double.parseDouble(treeNodeLink.getRuleLimitValue());
        case 5:
            return Double.parseDouble(pendingDecisionValue) >= Double.parseDouble(treeNodeLink.getRuleLimitValue());
        default:
            return false;
        }
    }
}
