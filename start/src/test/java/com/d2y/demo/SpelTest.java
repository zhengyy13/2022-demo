/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo;

import com.d2y.demo.api.model.UserModel;
import com.d2y.demo.common.component.MyServiceBean;
import com.d2y.demo.common.utils.SpringUtils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-08-16 10:33
 */
@SpringBootTest
public class SpelTest {

    @Test
    public void test() {
        // 创建ExpressionParser解析表达式
        ExpressionParser parser = new SpelExpressionParser();

        // 得到SpelExpression
        // 使用ctx.setVariable时，需要前缀
        SpelExpression exp = (SpelExpression) parser.parseExpression("#user.id");
        // 使用ctx.setRootObject时，不需要#符号和前缀
        // SpelExpression exp = (SpelExpression)parser.parseExpression("id");

        // 创建一个虚拟的容器EvaluationContext
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        // 向容器内添加bean
        UserModel user = new UserModel();
        user.setId(123L);
        ctx.setVariable("user", user);
        // ctx.setRootObject(user);

        // 得到user对象的id属性值
        String value = String.valueOf(exp.getValue(ctx));
        System.out.println(value);
    }

    @Test
    public void beanTest() {
        //解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        //上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(SpringUtils.getApplicationContext()));
        //表达式
        Expression expression = parser.parseExpression("@myServiceBean");
        MyServiceBean myServiceBean = expression.getValue(context, MyServiceBean.class);
        myServiceBean.hello();
    }
}
