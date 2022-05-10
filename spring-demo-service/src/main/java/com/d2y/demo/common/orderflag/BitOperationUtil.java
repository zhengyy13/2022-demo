package com.d2y.demo.common.orderflag;

/**
 * 位操作运算符
 */
public class BitOperationUtil {

    private static final String FLAG_FORMAT = "%020d";
    /**
     * 是否包含某个标记
     * @param orderFlag
     * @param flagVal
     * @return
     */
    public static boolean hasFlag(Integer orderFlag, Integer flagVal) {
        if (orderFlag == null) {
            return false;
        }
        return (flagVal & orderFlag) == flagVal;
    }

    /**
     * 位增加标记
     *
     * @param orderFlag
     * @param flagVal
     * @return
     */
    public static Integer addFlag(Integer orderFlag, Integer flagVal) {
        if (orderFlag == null) {
            return flagVal;
        }
        return orderFlag | flagVal;
    }

    /**
     * 去掉某个标记
     *
     * @param orderFlag
     * @param flagVal
     * @return
     */
    public static Integer removeFlag(Integer orderFlag, Integer flagVal) {
        if (!hasFlag(orderFlag, flagVal)) {
            return orderFlag;
        }
        int flagRevers = ~flagVal;
        return orderFlag & flagRevers;
    }

    /**
     * 整数转标记字符串
     *
     * @param flag
     * @return
     */
    public static String convertFlag(Integer flag) {
        String flagStr = Integer.toBinaryString(flag);
        int len = flagStr.length();
        if (len > 10) {
            for (int i = 0; i < 20 - len; i++) {
                flagStr = "0" + flagStr;
            }
        } else {
            flagStr = String.format(FLAG_FORMAT, Integer.parseInt(flagStr));
        }
        return flagStr;
    }

}
