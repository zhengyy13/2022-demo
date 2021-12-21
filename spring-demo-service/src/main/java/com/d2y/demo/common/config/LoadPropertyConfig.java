/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-15 16:43
 */
@Component
@PropertySource(value = { "classpath:properties/config.properties" })
//@ConfigurationProperties(prefix = "tenant")
public class LoadPropertyConfig {
    @Value("${tenant.ids}")
    private Integer[] tenantIds;

    public Integer[] getTenantIds() {
        return tenantIds;
    }

    public void setTenantIds(Integer[] tenantIds) {
        this.tenantIds = tenantIds;
    }
}
