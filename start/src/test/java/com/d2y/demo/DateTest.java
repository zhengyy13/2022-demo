/*
 * Copyright (c) 2001-2023 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2023-03-09 09:26
 */
public class DateTest {

    @Test
    public void yearBetween() throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String text1 = "2021-08-01";
        Temporal startDate = LocalDate.parse(text1);
        System.out.println("------- year --------------");
        // 方法返回为相差年数
        Date date = ft.parse(text1);
        long years = ChronoUnit.YEARS
            .between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
        System.out.println(years);
        System.out.println(years == 0 ? 1 : years);

        Calendar beginCal = Calendar.getInstance();
        beginCal.setTimeInMillis(date.getTime());

        Calendar endCal = Calendar.getInstance();
        endCal.setTimeInMillis(new Date().getTime());
        System.out.println(endCal.get(Calendar.YEAR));
        System.out.println(beginCal.get(Calendar.YEAR));
        int result = endCal.get(Calendar.YEAR) - beginCal.get(Calendar.YEAR);
        System.out.println("result："+result);
    }
}
