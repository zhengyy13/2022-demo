package com.d2y.demo.common.orderflag;

/**
 * 二进制
 */
public enum OrderFlagEnum {
    // code 注意 是二进制（1,2,4,8...）
    // 下单时 处方已审核通过
    PRESCRIPTION_APPROVED(1),
    //订单需要补处方 （先下单后补处方）
    SUPPLEMENT_PRESCRIPTION(2),
    //处方已核准发药
    CHECK_BILL_ORDER_FLAG(4),
    //处方已拆单
    PRESCRIPTION_SPLIT_ORDER(8),
    //复核调剂
    MAKE_UP_A_PRESCRIPTION(16),
    ;

    private Integer code;

    OrderFlagEnum(Integer code) {
        this.code = code;
    }

    /**
     * 获取标记位转化为int型数据
     *
     * @return
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 获取字面值
     *
     * @return
     */
    public Integer getFlag() {
        return code;
    }

}
