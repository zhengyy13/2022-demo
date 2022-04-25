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

import com.d2y.demo.design.design01.goods.DeliverReq;
import com.d2y.demo.design.design01.goods.GoodsService;
import com.d2y.demo.design.design01.zzz.IPrize;
import com.d2y.demo.design.design01.zzz.PrizeTypeEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-25 15:32
 */
@Component
@Slf4j
public class GoodsPrizeImpl implements IPrize {

    @Autowired
    private GoodsService goodsService;

    @Override
    public PrizeTypeEnum getPrizeTypeEnum() {
        return PrizeTypeEnum.GOODS;
    }

    @Override
    public void awardToUser(String userId, String prizeId, String bizId, Map<String, String> extMap) {
        DeliverReq deliverReq = new DeliverReq();
        deliverReq.setUserName(queryUserName(userId));
        deliverReq.setUserPhone(queryUserPhoneNumber(userId));
        deliverReq.setSku(prizeId);
        deliverReq.setOrderId(bizId);
        deliverReq.setConsigneeUserName(extMap.get("consigneeUserName"));
        deliverReq.setConsigneeUserPhone(extMap.get("consigneeUserPhone"));
        deliverReq.setConsigneeUserAddress(extMap.get("consigneeUserAddress"));

        Boolean isSuccess = goodsService.deliverGoods(deliverReq);

        log.info("请求参数[实物商品] => uId：{} commodityId：{} bizId：{} extMap：{}", userId, prizeId, bizId, JSON.toJSON(extMap));
        log.info("测试结果[实物商品]：{}", isSuccess);

        if (!isSuccess)
            throw new RuntimeException("实物商品发放失败");
    }

    private String queryUserName(String uId) {
        return "花花";
    }

    private String queryUserPhoneNumber(String uId) {
        return "15200101232";
    }
}
