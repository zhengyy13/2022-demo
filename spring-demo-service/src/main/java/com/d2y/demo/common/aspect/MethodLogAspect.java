/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.aspect;

import com.d2y.demo.common.annotation.MethodLog;
import com.d2y.demo.common.utils.SpelUtils;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.Pointcut;
import org.springframework.aop.support.Pointcuts;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-13 15:25
 */
@Aspect
@Component
@Slf4j
public class MethodLogAspect {

    @AfterReturning(pointcut = "@annotation(methodLog)", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, MethodLog methodLog, Object result) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String name = signature.getName();
        String id = methodLog.id();
        String content = methodLog.content();
        String remark = methodLog.remark();
        EvaluationContext evaluationContext = SpelUtils.getEvaluationContext(args, signature);
        String idVal = String.valueOf(SpelUtils.getElParseValue(id, evaluationContext));
        String remarkVal = String.valueOf(SpelUtils.getElParseValue(remark, evaluationContext));
        System.out.println("=================doAfterReturning：" + name);
        System.out.println(id + "：" + idVal);
        System.out.println(content);
        System.out.println(remark + "：" + remarkVal);
    }

    @Around("@annotation(methodLog)")
    public void doAround(ProceedingJoinPoint joinPoint, MethodLog methodLog) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("=================doAround start：" + name);
        joinPoint.proceed();
        System.out.println("=================doAround end：" + name);
    }

    @Before("@annotation(methodLog)")
    public void doBefore(JoinPoint joinPoint, MethodLog methodLog) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("=================doBefore：" + name);
    }

    @After("@annotation(methodLog)")
    public void doAfter(JoinPoint joinPoint, MethodLog methodLog) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String name = signature.getName();
        System.out.println("=================doAfter：" + name);
    }
}
