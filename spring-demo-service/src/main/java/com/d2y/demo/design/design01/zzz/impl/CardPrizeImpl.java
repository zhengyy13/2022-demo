/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.design.design01.zzz.impl;

import com.alibaba.fastjson.JSON;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.d2y.demo.design.design01.card.IQiYiCardService;
import com.d2y.demo.design.design01.zzz.IPrize;
import com.d2y.demo.design.design01.zzz.PrizeTypeEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-25 15:31
 */
@Component
@Slf4j
public class CardPrizeImpl implements IPrize {

    @Autowired
    private IQiYiCardService iQiYiCardService;

    @Override
    public PrizeTypeEnum getPrizeTypeEnum() {
        return PrizeTypeEnum.CARD;
    }

    @Override
    public void awardToUser(String userId, String prizeId, String bizId, Map<String, String> extMap) {
        String mobile = queryUserMobile(userId);
        iQiYiCardService.grantToken(mobile, bizId);
        log.info("请求参数[爱奇艺兑换卡] => uId：{} commodityId：{} bizId：{} extMap：{}", userId, prizeId, bizId,
            JSON.toJSON(extMap));
        log.info("测试结果[爱奇艺兑换卡]：success");
    }

    private String queryUserMobile(String uId) {
        return "15200101232";
    }
}
