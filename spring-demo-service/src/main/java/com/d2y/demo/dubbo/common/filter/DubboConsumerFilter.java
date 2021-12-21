/*
 * Copyright (c) 2001-2021 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo.dubbo.common.filter;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2021-12-20 15:22
 */
//@Activate(group = { CommonConstants.CONSUMER }, order = 1)
//@Slf4j
//public class DubboConsumerFilter implements Filter {
//    private static final Integer TIME_TWO_MILLI_SECONDS = 2000;
//    private static final Integer TIME_THREE_MILLI_SECONDS = 3000;
//    private static final Integer TIME_FOUR_MILLI_SECONDS = 4000;
//    private static final Integer LOG_A_BATCH_LENGTH = 10 * 1024;
//
//    private static List<String> ignoreMethods = Lists.newArrayList("org.apache.filter.monitor.MonitorService#collect");
//
//    @Override
//    public Result invoke(Invoker<?> invoker, Invocation invocation) {
//        // 获取IP地址
//        String clientIp = RpcContext.getContext().getRemoteHost();
//        String methodName = invoker.getInterface().getName() + "#" + invocation.getMethodName();
//        Object[] arguments = invocation.getArguments();
//        Object[] argumentsForLog = new Object[arguments.length];
//        for (int i = 0; i < arguments.length; i++) {
//            Object arg = arguments[i];
//            // 大于 10KB B的字节数组忽略掉，不记录日志
//            if ((arg instanceof byte[]) && ((byte[]) arg).length > LOG_A_BATCH_LENGTH) {
//                argumentsForLog[i] = "byte array size greater than 10KB, not log";
//            } else {
//                argumentsForLog[i] = arg;
//            }
//        }
//        long starttime = System.currentTimeMillis();
//        Result result = null;
//        try {
//            result = invoker.invoke(invocation);
//            return result;
//        } finally {
//            Boolean flag = true;
//            if (ignoreMethods.contains(methodName)) {
//                flag = false;
//            }
//
//            if (flag) {
//                long executeTime = System.currentTimeMillis() - starttime;
//                //日志对象
//                LogPrintModel logModel = getCloudLogPrintModel(clientIp, methodName, argumentsForLog, executeTime);
//                if (null != result && result.hasException()) {
//                    logModel.setErrorName(result.getException().getMessage());
//                    log.error("{}调用dubbo执行失败[发生异常]。{}", logModel.toCollectLog(), result.getException());
//                } else {
//                    if (null != result) {
//                        logModel.setResult(result.getValue());
//                    }
//                    String invokeLog = logModel.toCollectLog();
//                    log.info("[consumer] 调用dubbo成功。{}", invokeLog);
//                    if (executeTime > TIME_TWO_MILLI_SECONDS && executeTime <= TIME_THREE_MILLI_SECONDS) {
//                        log.warn("[consumer] 调用dubbo执行超时2-3秒内。{}", invokeLog);
//                    }
//                    if (executeTime > TIME_FOUR_MILLI_SECONDS) {
//                        log.error("[consumer] 调用dubbo执行超时3秒以上。{}", invokeLog);
//                    }
//                }
//            }
//        }
//
//    }
//
//    /**
//     * 设置日志对象
//     *
//     * @param clientIp
//     * @param methodName
//     * @param arguments
//     * @param elapsedTime
//     * @return
//     */
//
//    private LogPrintModel getCloudLogPrintModel(String clientIp, String methodName, Object[] arguments,
//        long elapsedTime) {
//        LogPrintModel logModel = new LogPrintModel();
//        logModel.setClientIp(clientIp);
//        logModel.setMethodName(methodName);
//        logModel.setParams(arguments);
//        logModel.setExecuteTime(elapsedTime);
//        return logModel;
//    }
//}
