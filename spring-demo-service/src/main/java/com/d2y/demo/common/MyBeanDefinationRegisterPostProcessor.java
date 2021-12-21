/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common;

import com.d2y.demo.common.annotation.MyService;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-13 13:59
 */
@Component
public class MyBeanDefinationRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("##########################：BeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry");
        System.out.println("MyBeanDefinationRegisterPostProcessor的postProcessBeanDefinitionRegistry方法");
        System.out.println("bean定义的数据量:"+registry.getBeanDefinitionCount());
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
            System.out.println(beanDefinition.getBeanClassName());
        }
        //定义一个扫描器
        /**
         * 参考：https://blog.csdn.net/yu_kang/article/details/88075447
         *
         * 具体来讲，缺省情况下，该工具类仅仅关注带有如下注解的类 :
         * @Component
         * @Repository
         * @Service
         * @Controller
         * @RestController
         * @ManagedBean (Java EE 6)
         * @Named （JSR-330）
         */
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.addIncludeFilter(new AnnotationTypeFilter(MyService.class));
        scanner.scan("com.d2y.demo");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
        throws BeansException {
        System.out.println("##########################：BeanDefinitionRegistryPostProcessor#postProcessBeanFactory");
        System.out.println("MyBeanDefinationRegisterPostProcessor的postProcessBeanFactory方法");
    }
}
