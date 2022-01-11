/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.ext.scanner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.cglib.transform.impl.AddInitTransformer;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-21 18:33
 */
public class MyDefaultClassPathScanner extends ClassPathBeanDefinitionScanner {

    private final String DEFAULT_MAPPER_SUFFIX = "Mapper";

    private String mapperManagerFactoryBean;

    public void setMapperManagerFactoryBean(String mapperManagerFactoryBean) {
        this.mapperManagerFactoryBean = mapperManagerFactoryBean;
    }

    public MyDefaultClassPathScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        // 如果指定的基础包路径中不存在任何类对象，则提示
        if (beanDefinitions.isEmpty()) {
            System.out.println("系统没有在 '" + Arrays.toString(basePackages) + "' 包中找到任何Mapper，请检查配置");
        } else {
            processBeanDefinitions(beanDefinitions);
        }
        return beanDefinitions;
    }

    /**
     * 注册过滤器-保证正确的类被扫描注入
     */

    protected void registerFilters() {
        addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
                throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith(DEFAULT_MAPPER_SUFFIX);
            }
        });
        addExcludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
                throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith("package-info");
            }
        });
    }

    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        return metadataReader.getClassMetadata().isInterface() && metadataReader.getClassMetadata().isIndependent();
    }

    /**
     * 重写父类的判断是否能够实例化的组件-该方法是在确认是否真的是isCandidateComponent
     * 原方法解释：
     * 确定给定的bean定义是否有资格成为候选人。
     * 默认实现检查类是否不是接口，也不依赖于封闭类。
     * 以在子类中重写。
     *
     * @param beanDefinition
     * @return
     */
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        // 原方法这里是判断是否为顶级类和是否是依赖类（即接口会被排除掉-由于我们需要将接口加进来，所以需要覆盖该方法）
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    public void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            definition = (GenericBeanDefinition) holder.getBeanDefinition();
            String mapperClassName = definition.getBeanClassName();
            // 必须在这里加入泛型限定，要不然在spring下会有循环引用的问题
            definition.getConstructorArgumentValues().addGenericArgumentValue(mapperClassName);
            // 依赖注入
            definition.getPropertyValues().add("mapperInterface", mapperClassName);
            // 根据工厂的名称创建出默认的BaseMapper实现
            definition.getPropertyValues()
                .add("mapperManagerFactoryBean", new RuntimeBeanReference(this.mapperManagerFactoryBean));
            definition.setBeanClass(BaseMapperFactoryBean.class);
            // 设置Mapper按照接口组装
            definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            System.out.println("已开启自动按照类型注入 '" + holder.getBeanName() + "'.");
        }
    }
}
