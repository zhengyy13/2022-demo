/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo;

import com.d2y.demo.common.orderflag.BitOperationUtil;
import com.d2y.demo.common.orderflag.OrderFlagEnum;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-20 16:50
 */
public class BizTest {
    private static final Integer MAX_OPTIONAL_LEVEL = 5;

    @Test
    public void getRebateLevelListTest(){
        int maxRebateFee = 453;
        List<Integer> rebateLevelList = getRebateLevelList(maxRebateFee, MAX_OPTIONAL_LEVEL);
        System.out.println(rebateLevelList);
    }
    private List<Integer> getRebateLevelList(int maxRebateFee, Integer maxOptionalLevel) {
        // 分转元处理
        maxRebateFee /= 100;
        // 返回返利列表，N等分取整，金额小于N时直接按 1元递减到0；
        List<Integer> serviceFeeList = Lists.newArrayList();
        if (maxRebateFee <= 0) {
            serviceFeeList.add(0);
        } else {
            int currSectionPoint = Math.min(maxRebateFee, maxOptionalLevel);
            for (int sectionPoint = currSectionPoint; sectionPoint >= 0; sectionPoint--) {
                int fee = (int) (maxRebateFee * sectionPoint * 1.0 / currSectionPoint);
                // 返回数据统一按分单位处理
                serviceFeeList.add(fee * 100);
            }
        }
        return serviceFeeList;
    }


    @Test
    public void statusTest() {
        //        short COST_ASSET = 1 << 0;
        //        short COST_GOLD = 1 << 1;
        //        short COST_BINDGOLD = 1 << 2;
        //        short COST_SOPH = 1 << 3;
        //        short COST_STRSOUL = 1 << 4;
        //        short COST_REFSOUL = 1 << 5;
        //        short COST_SOULSTONE = 1 << 6;
        //        short COST_AAMHID = 1 << 7;
        //        short COST_REALM = 1 << 8;
        //        System.out.println(COST_ASSET);
        //        System.out.println(COST_GOLD);
        //        System.out.println(COST_BINDGOLD);
        //        System.out.println(COST_SOPH);
        //        System.out.println(COST_STRSOUL);
        //        System.out.println(COST_REFSOUL);
        //        System.out.println(COST_SOULSTONE);
        //        System.out.println(COST_AAMHID);
        //        System.out.println(COST_REALM);


        Integer orderFlag = 0;
        printOrderFlagList(orderFlag);

        orderFlag = addOrderFlagList(orderFlag);
        printOrderFlagList(orderFlag);

    }

    private void printOrderFlagList(Integer orderFlag) {
        System.out.println(OrderFlagEnum.PRESCRIPTION_APPROVED.getCode() & orderFlag);
        System.out.println(OrderFlagEnum.SUPPLEMENT_PRESCRIPTION.getCode() & orderFlag);
        System.out.println(OrderFlagEnum.CHECK_BILL_ORDER_FLAG.getCode() & orderFlag);
        System.out.println(OrderFlagEnum.PRESCRIPTION_SPLIT_ORDER.getCode() & orderFlag);
        System.out.println(OrderFlagEnum.MAKE_UP_A_PRESCRIPTION.getCode() & orderFlag);
        System.out.println("-----------------------------------------------------------");
    }

    private Integer addOrderFlagList(Integer orderFlag) {
        orderFlag = BitOperationUtil.addFlag(orderFlag, OrderFlagEnum.PRESCRIPTION_APPROVED.getCode());
        orderFlag = BitOperationUtil.addFlag(orderFlag, OrderFlagEnum.SUPPLEMENT_PRESCRIPTION.getCode());
        orderFlag = BitOperationUtil.addFlag(orderFlag, OrderFlagEnum.CHECK_BILL_ORDER_FLAG.getCode());
        orderFlag = BitOperationUtil.addFlag(orderFlag, OrderFlagEnum.PRESCRIPTION_SPLIT_ORDER.getCode());
        orderFlag = BitOperationUtil.addFlag(orderFlag, OrderFlagEnum.MAKE_UP_A_PRESCRIPTION.getCode());
        System.out.println(orderFlag);
        return orderFlag;
    }
}
