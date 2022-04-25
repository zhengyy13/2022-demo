/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo;

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
public class PscBizTest {
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
}
