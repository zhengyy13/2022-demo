/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo;

import com.d2y.demo.design.design01.zzz.IPrize;
import com.d2y.demo.design.design01.zzz.PrizeFactory;
import com.d2y.demo.design.design01.zzz.PrizeTypeEnum;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-25 15:21
 */
@SpringBootTest
public class DesignTest {

    @Autowired
    private PrizeFactory prizeFactory;

    /**
     * 工厂模式
     */
    @Test
    void design01() {
        IPrize prize = prizeFactory.getPrize(PrizeTypeEnum.CARD.beanName());
        prize.awardToUser("10001", "9820198721311", "1023000020112221113", new HashMap<String, String>() {{
            put("consigneeUserName", "谢飞机");
            put("consigneeUserPhone", "15200292123");
            put("consigneeUserAddress", "吉林省.长春市.双阳区.XX街道.檀溪苑小区.#18-2109");
        }});
    }
}
