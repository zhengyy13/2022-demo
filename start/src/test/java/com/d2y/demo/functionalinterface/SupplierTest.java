/*
 * Copyright (c) 2001-2023 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.functionalinterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2023-03-13 13:31
 */
public class SupplierTest {

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(2);
        list.add(3);
        ZSupplier<Integer> zSupplier = () -> {
            if (CollectionUtils.isEmpty(list)) {
                return null;
            }
            Optional<Integer> max = list.stream().max(Comparator.comparingInt(o -> o));
            return max.get();
        };

        System.out.println(zSupplier.get());
    }
}
