/*
 * Copyright (c) 2001-2023 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.functionalinterface;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2023-03-09 13:43
 */
@FunctionalInterface
public interface ZConsumer<T> {
    void accept(T t);

    default ZConsumer<T> andThen(ZConsumer<? super T> after) {
        return (t) -> {
            accept(t);
            after.accept(t);
        };
    }
}
