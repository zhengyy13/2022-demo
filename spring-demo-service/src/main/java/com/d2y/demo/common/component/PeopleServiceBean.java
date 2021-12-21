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
import com.d2y.demo.common.ext.processor.PeopleService;
import com.d2y.demo.mybatis.entity.MybatisDemoUser;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-21 10:36
 */
@Component
public class PeopleServiceBean {
    @MyInjected(name = "women")
    //@Resource(name = "women")
    private PeopleService peopleService;

    public void peopleIntroduce() {
        peopleService.introduce();
    }

    @MethodLog(id = "#user.bizId", content = "新增用户", remark = "#user.remark")
    public Boolean add(MybatisDemoUser user){
        System.out.println(user);
        return true;
    }
}
