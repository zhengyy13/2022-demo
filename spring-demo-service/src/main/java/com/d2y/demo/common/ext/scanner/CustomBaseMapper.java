/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.ext.scanner;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-21 18:30
 */
public class CustomBaseMapper implements BaseMapper {

    @Override
    public void add(String value) {
        System.out.println("add：" + value);
    }

    @Override
    public void remove(String key) {
        System.out.println("remove：" + key);
    }
}
