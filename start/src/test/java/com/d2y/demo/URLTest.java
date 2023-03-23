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

        String url = "https://tianhe.wy.guahao.com/community/consult/search-doctor?diseaseId=14dcd023-31e2-11e6-804e-848f69fd6b70&deptCode=N18.900x005&consultResourcePool=970028625828423346&bizFullName=%E8%84%91%E8%A1%80%E7%AE%A1%E7%97%85%E5%90%8E%E9%81%97%E7%97%87&serviceCode=IMAGE_TEXT_230309_t8sp26q3&medicalType=13&noConsultCount=1&noArgReply=1&noDiseaseId=1";
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
