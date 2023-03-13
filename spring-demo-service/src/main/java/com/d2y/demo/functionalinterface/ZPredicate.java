/*
 * Copyright (c) 2001-2023 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.functionalinterface;

import java.util.Objects;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2023-03-09 10:31
 */
@FunctionalInterface
public interface ZPredicate<T> {
    boolean test(T t);

    default ZPredicate<T> and(ZPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return t -> test(t) && other.test(t);
    }

    default ZPredicate<T> negate() {
        return (t) -> !test(t);
    }

    default ZPredicate<T> or(ZPredicate<? super T> or) {
        return (t) -> test(t) || or.test(t);
    }

    static <T> ZPredicate<T> isEqual(Object targetRef) {
        return (null == targetRef) ? Objects::isNull : (t) -> targetRef.equals(t);
    }
}
