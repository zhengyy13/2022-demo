/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.design.design01.zzz;

import com.d2y.demo.common.utils.SpringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-25 15:37
 */
@Component
public class PrizeFactory implements InitializingBean {

    @Autowired
    private List<IPrize> prizeList;

    //    @Autowired
    //    private Map<String, IPrize> prizeMap;
    private Map<String, IPrize> prizeMap = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        /**
         * 默认处理设置
         * 多个实现对应一个处理需要报错
         * 实现类配置校验getPrizeTypeEnum非空，
         * 枚举对应的beanName非空
         */
        if (!CollectionUtils.isEmpty(prizeList)) {
            for (IPrize prize : prizeList) {
                if (null != prize.getPrizeTypeEnum()) {
                    prizeMap.put(prize.getPrizeTypeEnum().beanName(), prize);
                }
            }
        }
    }

    //    private Map<String, IPrize> prizeMap;
    //
    //    @Override
    //    public void afterPropertiesSet() throws Exception {
    //        prizeMap = SpringUtils.getBeansOfType(IPrize.class);
    //        System.out.println("初始化prizeMap");
    //    }
    //
    public IPrize getPrize(String prizeTypeName) {
        if (null == prizeMap) {
            return null;
        }
        if (StringUtils.isEmpty(prizeTypeName)) {
            //返回默认处理
            return null;
        }
        return prizeMap.get(prizeTypeName);
    }
}
