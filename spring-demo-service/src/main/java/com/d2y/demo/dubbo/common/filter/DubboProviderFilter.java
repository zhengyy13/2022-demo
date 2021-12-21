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
 * @since 2021-12-20 15:16
 */
//@Activate(group = { CommonConstants.PROVIDER }, order = 1)
//@Slf4j
//public class DubboProviderFilter implements Filter {
//
//    private static final Integer TIME_TWO_MILLI_SECONDS = 2000;
//    private static final Integer TIME_THREE_MILLI_SECONDS = 3000;
//    private static final Integer TIME_FOUR_MILLI_SECONDS = 4000;
//
//    private static List<String> ignoreMethods;
//
//    static {
//        ignoreMethods = Lists.newArrayList("org.apache.filter.monitor.MonitorService#collect");
//    }
//
//    @Override
//    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//        // 获取调用方IP地址
//        String clientIp = RpcContext.getContext().getRemoteHost();
//        String methodName = invoker.getInterface().getName() + "#" + invocation.getMethodName();
//        Object[] args = invocation.getArguments();
//        long starttime = System.currentTimeMillis();
//        LogPrintModel logModel = new LogPrintModel();
//        logModel.setClientIp(clientIp);
//        logModel.setMethodName(methodName);
//        logModel.setParams(args);
//
//        Result result = invoker.invoke(invocation);
//        if (ignoreMethods.contains(methodName)) {
//            return result;
//        }
//
//        long executeTime = System.currentTimeMillis() - starttime;
//        log.info("[provider] dubbo执行耗时{}ms，方法[{}]", executeTime, methodName);
//        logModel.setExecuteTime(executeTime);
//        if (result.hasException()) {
//            logModel.setErrorName(result.getException().getMessage());
//            log.error("{}dubbo执行失败[发生异常]。{}", logModel.toCollectLog(), result.getException());
//            return handleException(result.getException(), args,invocation);
//        } else if (!(result.getValue() instanceof com.guahao.convention.data.domain.Result)) {
//            logModel.setErrorName("返回结果类型错误");
//            log.error("dubbo执行失败[返回结果类型错误]。{}", logModel.toCollectLog());
//        } else if (result.getValue() == null) {
//            logModel.setErrorName("返回结果类型为null");
//            log.error("dubbo执行失败[返回结果类型错误]。{}", logModel.toCollectLog());
//        } else {
//            com.guahao.convention.data.domain.Result results = (com.guahao.convention.data.domain.Result) result
//                .getValue();
//            logModel.setResult(results);
//            String invokeLog = logModel.toCollectLog();
//            log.info("[provider] dubbo执行成功。{}", invokeLog);
//            if (executeTime > TIME_TWO_MILLI_SECONDS && executeTime <= TIME_THREE_MILLI_SECONDS) {
//                log.warn("[provider] dubbo执行超时2-3秒内。耗时{}ms，方法[{}]，参数[{}]", executeTime, methodName, getArgs(args));
//            }
//            if (executeTime > TIME_FOUR_MILLI_SECONDS) {
//                log.error("[provider] dubbo执行超时3秒以上。耗时{}ms，方法[{}]，参数[{}]", executeTime, methodName, getArgs(args));
//            }
//        }
//        return result;
//    }
//
//    private String getArgs(Object[] arguments) {
//        StringBuilder stringBuilder = new StringBuilder().append("[");
//        if (arguments != null && arguments.length > 0) {
//            for (int i = 0; i < arguments.length; i++) {
//                if (arguments[i] == null) {
//                    stringBuilder.append("arguments[").append(i).append("]是null").append(",");
//                } else {
//                    stringBuilder.append(arguments[i].getClass()).append("=").append(JsonUtil.write(arguments[i]))
//                        .append(",");
//                }
//            }
//        }
//        return stringBuilder.append("]").toString();
//    }
//
//    private Result handleException(Throwable exception, Object[] args, Invocation invocation) {
//        if (exception instanceof ServiceErrorException || exception instanceof ServiceException) {
//            return new AppResponse(exception.getMessage());
//        } else {
//            return AsyncRpcResult.newDefaultAsyncResult("系统异常_pmcService",invocation);
//        }
//    }
//}