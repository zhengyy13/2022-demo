package com.d2y.demo.common.orderflag;

/**
 * 订单标记 工具列
 */
public class OrderFlagUtil {

    /**
     * 获取下单时处方的状态
     * @param orderFlag
     * @return
     */
    public static boolean getPrescriptionApprovedStatusFlag (Integer orderFlag) {
        if (orderFlag == null) {
            return false;
        }
        return  BitOperationUtil.hasFlag(orderFlag,OrderFlagEnum.PRESCRIPTION_APPROVED.getCode());

    }
    /**
     * 设置下单时处方的状态
     * @param orderFlag
     * @return
     */
    public static Integer setPrescriptionApprovedStatusFlag (Integer orderFlag) {
        return  BitOperationUtil.addFlag(orderFlag,OrderFlagEnum.PRESCRIPTION_APPROVED.getCode());

    }

    /**
     * 订单标记： 先下单后补方
     * @param orderFlag
     * @return
     */
    public  static boolean getSupplementPrescritionFlag(Integer orderFlag){
        if (orderFlag == null) {
            return false;
        }
        return  BitOperationUtil.hasFlag(orderFlag,OrderFlagEnum.SUPPLEMENT_PRESCRIPTION.getCode());
    }

    /**
     * 设置 订单标记：先下单后部分
     * @param orderFlag
     * @return
     */
    public static Integer setSupplementPrescritionFlag(Integer orderFlag) {
        return BitOperationUtil.addFlag(orderFlag, OrderFlagEnum.SUPPLEMENT_PRESCRIPTION.getCode());
    }

    public static Integer setCheckBillOrderFlag(Integer orderFlag){
        return BitOperationUtil.addFlag(orderFlag,OrderFlagEnum.CHECK_BILL_ORDER_FLAG.getCode());
    }


    /**
     * 订单标记： 处方 已拆单
     * @param orderFlag
     * @return
     */
    public  static boolean getPrescritionSplitOrderFlag(Integer orderFlag){
        if (orderFlag == null) {
            return false;
        }
        return  BitOperationUtil.hasFlag(orderFlag,OrderFlagEnum.PRESCRIPTION_SPLIT_ORDER.getCode());
    }

    /**
     * 设置 订单标记：处方 已拆单
     * @param orderFlag
     * @return
     */
    public static Integer setPrescritionSplitOrderFlag(Integer orderFlag) {
        return BitOperationUtil.addFlag(orderFlag, OrderFlagEnum.PRESCRIPTION_SPLIT_ORDER.getCode());
    }


}
