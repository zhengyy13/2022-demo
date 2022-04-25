/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.design.design01.zzz;

import java.util.Map;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-25 15:26
 */
public interface IPrize {
    PrizeTypeEnum getPrizeTypeEnum();
    void awardToUser(String userId, String prizeId, String bizId, Map<String, String> extMap);
}
