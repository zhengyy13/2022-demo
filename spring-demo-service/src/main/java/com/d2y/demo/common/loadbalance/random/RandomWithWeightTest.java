/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.common.loadbalance.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 随机加权
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-02-08 17:07
 */
public class RandomWithWeightTest {
    private static HashMap<String, Integer> serverMap = new HashMap<>();

    static {
        serverMap.put("192.168.0.0:111", 1);
        serverMap.put("192.168.0.1:111", 1);
        serverMap.put("192.168.0.2:111", 1);
        serverMap.put("192.168.0.3:111", 1);
        serverMap.put("192.168.0.4:111", 2);
    }

    public static String getServer() {
        List<String> serverList = new ArrayList<>();
        HashMap<String, Integer> tempMap = new HashMap<>();
        tempMap.putAll(serverMap);
        for (String key : tempMap.keySet()) {
            for (int i = 0; i < tempMap.get(key); i++) {
                serverList.add(key);
            }
        }
        int index = new Random().nextInt(serverList.size());
        return serverList.get(index);
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
