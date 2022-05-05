/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.web;

import com.d2y.annotation.Log;
import com.d2y.demo.api.model.UserModel;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-05-05 17:35
 */
@RestController
@Component
public class StarterController {

    @Log(desc = "wo shi d2y")
    @GetMapping("/testLogStarter")
    public UserModel testLogStarter() {
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setName("xxxx");
        userModel.setAge(18);
        return userModel;
    }
}
