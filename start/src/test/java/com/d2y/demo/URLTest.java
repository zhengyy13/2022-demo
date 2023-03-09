/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;

import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-11-25 15:32
 */
public class URLTest {

    @Test
    public void decoderTest() throws ParseException {
        //String url = "https%253A%252F%252Fwww.bqex.com%252Fcode%252Fams%252Fweb%252Fjump%253Furl%253Dhttps%25253A%25252F%25252Fwww.bqex.com%25252Fyjs-ams%25252Fcloak-room%25252Fresult%25253Ffailed%25253Dfalse%252526frontSkipUrl%25253Dhttps%2525253A%2525252F%2525252Fwww.bqex.com%2525252Fbuyer%2525252Forder";

        String url = "https://bqex.com/ams?url=%2Fyjs-ams%2Fmanage%2Fuser-manage%3Ftoken%3D8a436639-d2e8-4cd6-a84f-ffd3465dbabd";
        try {
            while (!url.equals(URLDecoder.decode(url, "utf-8"))){
                url = URLDecoder.decode(url, "utf-8");
            }
            System.out.println(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
