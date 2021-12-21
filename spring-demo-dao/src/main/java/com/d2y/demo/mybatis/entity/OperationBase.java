/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.mybatis.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-13 15:12
 */
@Data
public class OperationBase {
    private Long bizId;

    private String remark;
}
