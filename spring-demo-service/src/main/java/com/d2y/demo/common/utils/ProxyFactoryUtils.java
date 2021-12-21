/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.utils;

import java.lang.reflect.Method;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-21 09:32
 */
public class ProxyFactoryUtils {
    public static Object getProxy(Class type, Object bean) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(type);
        proxyFactory.setTarget(bean);

        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] objects, Object o) throws Throwable {
                System.out.println("before：" + method.getName());
            }
        });

        proxyFactory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
                System.out.println("afterReturning：" + method.getName());
            }
        });

        proxyFactory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                System.out.println("around start：" + methodInvocation.getMethod().getName());
                Object proceed = methodInvocation.proceed();
                System.out.println("around end：" + methodInvocation.getMethod().getName());
                return proceed;
            }
        });
        return proxyFactory.getProxy();
    }
}
