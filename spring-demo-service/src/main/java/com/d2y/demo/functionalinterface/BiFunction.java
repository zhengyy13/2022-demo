/*
 * Copyright (c) 2001-2023 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.functionalinterface;

import java.util.function.Function;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2023-03-15 19:20
 */
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);

    default <V> BiFunction<T, U, V> andThen(Function<R, V> after) {
        return (T t, U u) -> after.apply(apply(t, u));
    }
}
