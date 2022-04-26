/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.design.design02.model.vo;

import java.util.List;

import lombok.Data;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-26 09:56
 */
@Data
public class TreeNode {
    /**
     * 规则树ID
     */
    private Long treeId;

    /**
     *规则树节点ID
     */
    private Long treeNodeId;

    /**
     * 1-节点 2-叶子节点
     */
    private Integer nodeType;

    /**
     * nodeType=2时，最终结果
     */
    private String nodeValue;

    private String ruleKey;

    private String ruleDesc;

    /**
     * 节点链路
     */
    private List<TreeNodeLink> treeNodeLinkList;
}
