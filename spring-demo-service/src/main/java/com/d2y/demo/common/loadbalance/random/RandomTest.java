/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.loadbalance.random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 随机
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-02-08 15:47
 */
public class RandomTest {
    /**
     * 集群地址列表
     */
    private static String[] groups = { "192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111",
        "192.168.0.4:111" };

    public static String getServer() {
        List<String> serverList = new ArrayList<>(Arrays.asList(groups));
        int nextInt = new Random().nextInt(serverList.size());
        return serverList.get(nextInt);
    }

    public static void main(String[] args) {
        HashMap<String, Integer> serverMap = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            String server = getServer();
            if (serverMap.containsKey(server)) {
                serverMap.put(server, serverMap.get(server) + 1);
            } else {
                serverMap.put(server, 1);
            }
        }

        serverMap.forEach((k, v) -> {
            System.out.println("group " + k + "：" + v + "(" + v / 1000D + "%)");
        });
    }
}
