package com.d2y.demo.design.design01.zzz;

import java.util.Objects;

/**
 * 区域限制类型 1-优先供应 2-仅供应
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-15 13:53
 */
public enum PrizeTypeEnum {

    CARD("cardPrize", "CARD"),
    COUPON("couponPrize", "COUPON"),
    GOODS("goodsPrize", "222");

    private String beanName;

    private String message;

    PrizeTypeEnum(String beanName, String message) {
        this.beanName = beanName;
        this.message = message;
    }

    public String beanName() {
        return beanName;
    }

    public static PrizeTypeEnum get(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }
        for (PrizeTypeEnum enu : values()) {
            if (code.equals(enu.beanName())) {
                return enu;
            }
        }
        return null;
    }
}
