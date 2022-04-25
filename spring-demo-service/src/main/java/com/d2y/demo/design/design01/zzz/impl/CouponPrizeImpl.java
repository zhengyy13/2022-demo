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

import com.d2y.demo.design.design01.coupon.CouponResult;
import com.d2y.demo.design.design01.coupon.CouponService;
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
public class CouponPrizeImpl implements IPrize {

    @Autowired
    private CouponService couponService;

    @Override
    public PrizeTypeEnum getPrizeTypeEnum() {
        return PrizeTypeEnum.COUPON;
    }

    @Override
    public void awardToUser(String userId, String prizeId, String bizId, Map<String, String> extMap) {
        CouponResult couponResult = couponService.sendCoupon(userId, prizeId, bizId);
        log.info("请求参数[优惠券] => uId：{} commodityId：{} bizId：{} extMap：{}", userId, prizeId, bizId, JSON.toJSON(extMap));
        log.info("测试结果[优惠券]：{}", JSON.toJSON(couponResult));
        if (!"0000".equals(couponResult.getCode()))
            throw new RuntimeException(couponResult.getInfo());
    }
}
