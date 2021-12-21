/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.utils;

import java.lang.reflect.Method;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import lombok.experimental.UtilityClass;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-13 16:09
 */
@UtilityClass
public class SpelUtils {

    public EvaluationContext getEvaluationContext(Object[] args, MethodSignature methodSignature) {
        if (args == null || args.length == 0) {
            return null;
        }
        EvaluationContext context = new StandardEvaluationContext();
        LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
        Method method = methodSignature.getMethod();
        String[] parameterNames = discoverer.getParameterNames(method);
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        return context;
    }

    public Object getElParseValue(String el, EvaluationContext context) {
        if (StringUtils.isEmpty(el)) {
            return null;
        }
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(el);
        try {
            Object value = expression.getValue(context);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
