/*
 * Copyright (c) 2001-2023 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.functionalinterface;

import com.d2y.demo.functionalinterface.ZPredicate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * 匿名函数的使用
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2023-03-09 09:40
 */
public class PredicateTest {

    ZPredicate<BigDecimal> zPredicate = amt -> {
        if (Objects.nonNull(amt)) {
            amt = amt.setScale(2, RoundingMode.HALF_UP);
            if (amt.compareTo(BigDecimal.ZERO) > 0) {
                return true;
            }
        }
        return false;
    };

    @Test
    public void test() {
        // test
        System.out.println(zPredicate.test(new BigDecimal("0.0002")));
    }

    @Test
    public void and() {
        // and
        ZPredicate<BigDecimal> zPredicate1 = zPredicate.and(amt -> amt.compareTo(new BigDecimal("100")) < 0);
        System.out.println(zPredicate1.test(new BigDecimal("101")));
    }

    @Test
    public void negate() {
        ZPredicate<Integer> zPredicate = x -> x % 2 == 0;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Integer> collect = list.stream().filter(zPredicate::test).collect(Collectors.toList());
        System.out.println(collect);

        ZPredicate<Integer> negate = zPredicate.negate();
        List<Integer> collect2 = list.stream().filter(negate::test).collect(Collectors.toList());
        System.out.println(collect2);
    }

    @Test
    public void or() {
        ZPredicate<Integer> zPredicate = x -> x > 5;
        ZPredicate<Integer> zPredicate1 = zPredicate.or(x -> x < 3);
        System.out.println(zPredicate1.test(6));
        System.out.println(zPredicate1.test(4));
    }

    @Test
    public void equals() {
        ZPredicate<String> zPredicate = ZPredicate.isEqual("zyy");
        List<String> list = new ArrayList<>();
        list.add("zyy1");
        list.add("zyy");
        List<String> collect = list.stream().filter(zPredicate::test).collect(Collectors.toList());
        System.out.println(collect);
    }
}
