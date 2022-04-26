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
import com.d2y.demo.design.design02.model.vo.TreeNode;
import com.d2y.demo.design.design02.model.vo.TreeNodeLink;
import com.d2y.demo.design.design02.model.vo.TreeRoot;
import com.d2y.demo.design.design02.service.logic.LogicFilter;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-26 10:56
 */
public abstract class BaseEngine implements IEngine {

    @Autowired
    private EngineConfig engineConfig;

    @Override
    public abstract EngineResult process(Long treeId, Long userId, TreeRich treeRich, Map<String, String> data);

    protected TreeNode engineDecisionMaker(Long treeId, Long userId, TreeRich treeRich, Map<String, String> data) {
        TreeRoot treeRoot = treeRich.getTreeRoot();
        Map<Long, TreeNode> treeNodeMap = treeRich.getTreeNodeMap();
        Long treeRootNodeId = treeRoot.getTreeRootNodeId();
        TreeNode treeNode = treeNodeMap.get(treeRootNodeId);
        while (treeNode.getNodeType() == 1) {
            LogicFilter logicFilter = engineConfig.getLogicFilter(treeNode.getRuleKey());
            String pendingDecisionValue = logicFilter.getPendingDecisionValue(treeId, userId, data);
            List<TreeNodeLink> treeNodeLinkList = treeNode.getTreeNodeLinkList();

            Long nextNodeId = logicFilter.filter(pendingDecisionValue, treeNodeLinkList);
            treeNode = treeNodeMap.get(nextNodeId);
        }
        return treeNode;
    }
}
