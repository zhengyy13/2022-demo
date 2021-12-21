/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.ext.processor;

import com.d2y.demo.common.annotation.MyInjected;
import com.d2y.demo.common.utils.ProxyFactoryUtils;
import com.d2y.demo.common.utils.SpringUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-21 09:29
 */
@Component
public class MyInjectedProcessor implements BeanPostProcessor, ApplicationContextAware, PriorityOrdered {

    private ApplicationContext applicationContext;

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("##########################：BeanPostProcessor#postProcessBeforeInitialization");
        System.out.println("postProcessBeforeInitialization：" + beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("##########################：BeanPostProcessor#postProcessAfterInitialization");
        System.out.println("postProcessAfterInitialization：" + beanName);
        if(beanName.equals("peopleServiceBean")){
            System.out.println("@@@@@@@@@@@@@@@@@@@："+bean.getClass());
        }
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MyInjected.class)) {
                Class<?> type = field.getType();
                if (!type.isInterface()) {
                    throw new BeanCreationException(
                        "MyInjected field must be declared as an interface:" + field.getName() + " @Class " + clazz
                            .getName());
                }
                try {
                    handleMyInjected(field, bean, type);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return bean;
    }

    private void handleMyInjected(Field field, Object bean, Class type) throws IllegalAccessException {
        Map<String, Object> beansOfType = this.applicationContext.getBeansOfType(type);
        if (CollectionUtils.isEmpty(beansOfType)) {
            throw new IllegalArgumentException("Not Find beans for type: " + type);
        }
        field.setAccessible(true);
        if (beansOfType.size() == 1) {
            Object next = beansOfType.values().iterator().next();
            Object proxy = ProxyFactoryUtils.getProxy(type, next);
            field.set(bean, proxy);
        } else {
            MyInjected myInjected = field.getAnnotation(MyInjected.class);
            String beanName = myInjected.name();
            Object beanByName = beansOfType.get(beanName);
            if (Objects.isNull(beanByName)) {
                throw new IllegalArgumentException("Not Find beans for name: " + beanName);
            }
            Object proxy = ProxyFactoryUtils.getProxy(type, beanByName);
            field.set(bean, proxy);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("##########################：ApplicationContextAware");
        this.applicationContext = applicationContext;
    }

    /**
     * 因为PeopleServiceBean类添加了aop，会被其他的postProcessor处理成代理对象，导致PeopleService注入失败，所以提高优先级
     * @return
     */
    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
