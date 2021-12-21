/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.component;

import com.d2y.demo.common.annotation.MethodLog;
import com.d2y.demo.common.annotation.MyInjected;
import com.d2y.demo.common.annotation.MyService;
import com.d2y.demo.common.ext.processor.PeopleService;
import com.d2y.demo.mybatis.entity.MybatisDemoUser;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-13 14:26
 */
@MyService
public class MyServiceBean {

    @MyInjected(name = "man")
    //@Resource(name = "man")
    private PeopleService peopleService;

    public void hello() {
        System.out.println("MyServiceBean hello");
    }

    @MethodLog(id = "#user.bizId", content = "新增用户", remark = "#user.remark")
    public Boolean add(MybatisDemoUser user){
        System.out.println(user);
        return true;
    }

    public void peopleIntroduce() {
        peopleService.introduce();
    }
}

