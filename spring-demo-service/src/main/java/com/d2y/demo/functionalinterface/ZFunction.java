/*
 * Copyright (c) 2001-2023 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.functionalinterface;

import java.util.Objects;
import java.util.function.Function;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2023-03-09 14:10
 */
@FunctionalInterface
public interface ZFunction<T, R> {

    R apply(T t);

    default <V> ZFunction<V, R> compose(ZFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return t -> apply(before.apply(t));
    }

    default <V> ZFunction<T, V> andThen(ZFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return t -> after.apply(apply(t));
    }

    static <T> ZFunction<T, T> identity() {
        return t -> t;
    }
}
