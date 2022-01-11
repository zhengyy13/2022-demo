/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.ext.scanner;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-01-10 17:02
 */
public class MapperManagerFactoryBean implements FactoryBean<BaseMapper>, InitializingBean,
    ApplicationListener<ApplicationEvent> {

    @Override
    public BaseMapper getObject() throws Exception {
        return new CustomBaseMapper();
    }

    @Override
    public Class<?> getObjectType() {
        return BaseMapper.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //TODO 判断属性的注入是否正确
    }

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());
    }
}
