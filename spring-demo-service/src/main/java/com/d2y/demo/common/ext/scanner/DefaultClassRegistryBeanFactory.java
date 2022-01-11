/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.ext.scanner;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-01-10 17:39
 */
public class DefaultClassRegistryBeanFactory implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor,
    BeanNameAware {

    private String scanPackage;

    private String beanName;

    private String mapperManagerFactoryBean;

    private ApplicationContext applicationContext;

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    public String getMapperManagerFactoryBean() {
        return mapperManagerFactoryBean;
    }

    public void setMapperManagerFactoryBean(String mapperManagerFactoryBean) {
        this.mapperManagerFactoryBean = mapperManagerFactoryBean;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        if (StringUtils.isEmpty(this.scanPackage)) {
            throw new IllegalArgumentException("scanPackage can't be null");
        }
        String basePackage2 = this.applicationContext.getEnvironment().resolvePlaceholders(this.scanPackage);
        String[] packages = StringUtils.tokenizeToStringArray(basePackage2, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
        MyDefaultClassPathScanner defaultClassPathScanner = new MyDefaultClassPathScanner(beanDefinitionRegistry);
        defaultClassPathScanner.setMapperManagerFactoryBean(mapperManagerFactoryBean);
        defaultClassPathScanner.registerFilters();

        defaultClassPathScanner.doScan(packages);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
