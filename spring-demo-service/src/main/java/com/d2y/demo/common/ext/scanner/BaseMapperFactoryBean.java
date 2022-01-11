/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.ext.scanner;

import java.lang.reflect.Proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-01-10 17:00
 */
public class BaseMapperFactoryBean<T> implements FactoryBean<T>, InitializingBean,
    ApplicationListener<ApplicationEvent>, ApplicationContextAware {
    /**
     * 要注入的接口类定义
     */
    private Class<T> mapperInterface;

    /**
     * Spring上下文
     */
    private ApplicationContext applicationContext;

    //也因该走工厂方法注入得来

    private BaseMapper mapperManagerFactoryBean;

    public BaseMapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        //采用动态代理生成接口实现类，核心实现
        return (T) Proxy.newProxyInstance(applicationContext.getClassLoader(), new Class[]{mapperInterface}, new MapperJavaProxy(mapperManagerFactoryBean, mapperInterface));
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //TODO 判断属性的注入是否正确-如mapperInterface判空
        if (null == mapperInterface)
            throw new IllegalArgumentException("Mapper Interface Can't Be Null!!");
    }

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //TODO 可依据事件进行扩展
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }


    public void setMapperManagerFactoryBean(BaseMapper mapperManagerFactoryBean) {
        this.mapperManagerFactoryBean = mapperManagerFactoryBean;
    }
}
