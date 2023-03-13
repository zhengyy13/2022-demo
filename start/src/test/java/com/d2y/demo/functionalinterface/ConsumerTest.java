/*
 * Copyright (c) 2001-2023 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.functionalinterface;

import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2023-03-09 13:43
 */
public class ConsumerTest {

    ZConsumer<Integer> zConsumer = x -> {
        x = x + x;
        System.out.println(x);
    };

    @Test
    public void accpet() {
        zConsumer.accept(2);
    }

    @Test
    public void andThen() {
        ZConsumer<Integer> zConsumer1 = this.zConsumer.andThen(x -> {
            x = x * x;
            System.out.println(x);
        });
        zConsumer1.accept(2);
    }
}
