/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.ext.importbean;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-01-11 10:46
 */
@Configuration
@Import({ConfigA.class, ConfigB.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class Config {

    @Resource
    ConfigA configA;

    @Resource
    ConfigB configB;

    @Resource
    ConfigC configC;

    @Resource
    ConfigD configD;


    public void print() {
        configA.print();
        configB.print();
        configC.print();
        configD.print();
    }
}
