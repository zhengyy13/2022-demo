/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.ext.scanner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-01-10 17:04
 */
public class MapperJavaProxy implements InvocationHandler {

    private BaseMapper baseMapper;

    private Class<?> interfaceClass;

    public MapperJavaProxy(BaseMapper baseMapper, Class<?> interfaceClass) {
        this.baseMapper = baseMapper;
        this.interfaceClass = interfaceClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException("mapperInterface is not interface.");
        }

        if (baseMapper == null) {
            baseMapper = new CustomBaseMapper();
        }
        return method.invoke(baseMapper, args);
    }
}
