/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.d2y.demo;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO
 *
 * @author zhengyy1
 * @version V1.0
 * @since 2022-04-19 10:26
 */
//@SpringBootTest
public class CompletableFutureTest {

    /**
     * 子线程是异步执行的，主线程休眠等待子线程执行完成，子线程执行完成后唤醒主线程，主线程获取任务执行结果后退出
     * 子线程执行异常时其异常会被捕获，然后修改任务的状态为异常结束并唤醒等待的主线程，get方法判断任务状态发生变更，就终止等待了，并抛出异常
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    void submitTest() throws ExecutionException, InterruptedException {
        // 创建异步执行任务:
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Double> cf = executorService.submit(() -> {
            System.out.println(Thread.currentThread() + " start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (false) {
                throw new RuntimeException("test");
            } else {
                System.out.println(Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
                return 1.2;
            }
        });
        System.out.println("main thread start,time->" + System.currentTimeMillis());
        //等待子任务执行完成,如果已完成则直接返回结果
        //如果执行任务异常，则get方法会把之前捕获的异常重新抛出
        System.out.println("run result->" + cf.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    /**
     * supplyAsync表示创建带返回值的异步任务的，相当于ExecutorService submit(Callable<T> task) 方法
     *
     * @throws Exception
     */
    @Test
    public void supplyAsyncTest() throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务，有返回值
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (false) {
                throw new RuntimeException("test");
            } else {
                System.out.println(Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
                return 1.2;
            }
        }, pool);
        System.out.println("main thread start,time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->" + cf.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    /**
     * runAsync表示创建无返回值的异步任务，相当于ExecutorService submit(Runnable task)方法
     *
     * @throws Exception
     */
    @Test
    public void runAsyncTest() throws Exception {
        // 创建异步执行任务，无返回值
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread() + " start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (false) {
                throw new RuntimeException("test");
            } else {
                System.out.println(Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
            }
        });
        System.out.println("main thread start,time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->" + cf.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    /**
     * thenApply / thenApplyAsync
     * thenApply 表示某个任务执行完成后执行的动作，即回调方法，会将该任务的执行结果即方法返回值作为入参传递到回调方法中
     * thenApplyAsync与thenApply的区别在于，前者是将job2提交到线程池中异步执行，实际执行job2的线程可能是另外一个线程
     * 后者是由执行job1的线程立即执行job2，即两个job都是同一个线程执行的
     *
     * @throws Exception
     */
    @Test
    public void thenApplyTest() throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        }, pool);
        //cf关联的异步任务的返回值作为方法入参，传入到thenApply的方法中
        //thenApply这里实际创建了一个新的CompletableFuture实例
        CompletableFuture<String> cf2 = cf.thenApply((result) -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return "test:" + result;
        });
        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->" + cf.get());
        System.out.println("main thread start cf2.get(),time->" + System.currentTimeMillis());
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    /**
     * thenAccept / thenRun
     * thenAccept 同 thenApply 接收上一个任务的返回值作为参数，但是无返回值；
     * thenRun 的方法没有入参，也没有返回值
     *
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        }, pool);
        //cf关联的异步任务的返回值作为方法入参，传入到thenApply的方法中
        CompletableFuture cf2 = cf.thenApply((result) -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return "test:" + result;
        }).thenAccept((result) -> { //接收上一个任务的执行结果作为入参，但是没有返回值
            System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(result);
            System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
        }).thenRun(() -> { //无入参，也没有返回值
            System.out.println(Thread.currentThread() + " start job4,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("thenRun do something");
            System.out.println(Thread.currentThread() + " exit job4,time->" + System.currentTimeMillis());
        });
        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->" + cf.get());
        System.out.println("main thread start cf2.get(),time->" + System.currentTimeMillis());
        //cf2 等待最后一个thenRun执行完成
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    /**
     * exceptionally方法指定某个任务执行异常时执行的回调方法，会将抛出异常作为参数传递到回调方法中
     * 如果该任务正常执行则会exceptionally方法返回的CompletionStage的result就是该任务正常执行的结果
     *
     * @throws Exception
     */
    @Test
    public void exceptionallyTest() throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "job1 start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (false) {
                throw new RuntimeException("test");
            } else {
                System.out.println(Thread.currentThread() + "job1 exit,time->" + System.currentTimeMillis());
                return 1.2;
            }
        }, pool);
        //cf执行异常时，将抛出的异常作为入参传递给回调方法
        CompletableFuture<Double> cf2 = cf.exceptionally((param) -> {
            System.out.println(Thread.currentThread() + " start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("error stack trace->");
            param.printStackTrace();
            System.out.println(Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
            return -1.1;
        });
        //cf正常执行时执行的逻辑，如果执行异常则不调用此逻辑
        CompletableFuture cf3 = cf.thenAccept((param) -> {
            System.out.println(Thread.currentThread() + "job2 start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("param->" + param);
            System.out.println(Thread.currentThread() + "job2 exit,time->" + System.currentTimeMillis());
        });
        System.out.println("main thread start,time->" + System.currentTimeMillis());
        //等待子任务执行完成,此处无论是job2和job3都可以实现job2退出，主线程才退出，如果是cf，则主线程不会等待job2执行完成自动退出了
        //cf2.get时，没有异常，但是依然有返回值，就是cf的返回值
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    /**
     * whenComplete是当某个任务执行完成后执行的回调方法，会将执行结果或者执行期间抛出的异常传递给回调方法
     * 如果是正常执行则异常为null，回调方法对应的CompletableFuture的result和该任务一致
     * 如果该任务正常执行，则get方法返回执行结果，如果是执行异常，则get方法抛出异常
     *
     * @throws Exception
     */
    @Test
    public void whenCompleteTest() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "job1 start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (false) {
                throw new RuntimeException("test");
            } else {
                System.out.println(Thread.currentThread() + "job1 exit,time->" + System.currentTimeMillis());
                return 1.2;
            }
        });
        //cf执行完成后会将执行结果和执行过程中抛出的异常传入回调方法，如果是正常执行的则传入的异常为null
        CompletableFuture<Double> cf2 = cf.whenComplete((a, b) -> {
            System.out.println(Thread.currentThread() + "job2 start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (b != null) {
                System.out.println("error stack trace->");
                b.printStackTrace();
            } else {
                System.out.println("run succ,result->" + a);
            }
            System.out.println(Thread.currentThread() + "job2 exit,time->" + System.currentTimeMillis());
        });
        //等待子任务执行完成
        System.out.println("main thread start wait,time->" + System.currentTimeMillis());
        //如果cf是正常执行的，cf2.get的结果就是cf执行的结果
        //如果cf是执行异常，则cf2.get会抛出异常
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    /**
     * @throws Exception
     */
    @Test
    public void handleTest() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "job1 start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (true) {
                throw new RuntimeException("test");
            } else {
                System.out.println(Thread.currentThread() + "job1 exit,time->" + System.currentTimeMillis());
                return 1.2;
            }
        });
        //cf执行完成后会将执行结果和执行过程中抛出的异常传入回调方法，如果是正常执行的则传入的异常为null
        CompletableFuture<String> cf2 = cf.handle((a, b) -> {
            System.out.println(Thread.currentThread() + "job2 start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (b != null) {
                System.out.println("error stack trace->");
                b.printStackTrace();
            } else {
                System.out.println("run succ,result->" + a);
            }
            System.out.println(Thread.currentThread() + "job2 exit,time->" + System.currentTimeMillis());
            if (b != null) {
                return "run error";
            } else {
                return "run succ";
            }
        });
        //等待子任务执行完成
        System.out.println("main thread start wait,time->" + System.currentTimeMillis());
        //get的结果是cf2的返回值，跟cf没关系了
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    /**
     * thenCombine / thenAcceptBoth / runAfterBoth
     * thenCombine会将两个任务的执行结果作为方法入参传递到指定方法中，且该方法有返回值；
     * thenAcceptBoth同样将两个任务的执行结果作为方法入参，但是无返回值；
     * runAfterBoth没有入参，也没有返回值。
     * 注意两个任务中只要有一个执行异常，则将该异常信息作为指定任务的执行结果
     *
     * @throws Exception
     */
    @Test
    public void thenCombineThenAcceptBothRunAfterBothTest() throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        });
        CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return 3.2;
        });
        //cf和cf2的异步任务都执行完成后，会将其执行结果作为方法入参传递给cf3,且有返回值
        CompletableFuture<Double> cf3 = cf.thenCombine(cf2, (a, b) -> {
            System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
            System.out.println("job3 param a->" + a + ",b->" + b);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
            return a + b;
        });

        //cf和cf2的异步任务都执行完成后，会将其执行结果作为方法入参传递给cf3,无返回值
        CompletableFuture cf4 = cf.thenAcceptBoth(cf2, (a, b) -> {
            System.out.println(Thread.currentThread() + " start job4,time->" + System.currentTimeMillis());
            System.out.println("job4 param a->" + a + ",b->" + b);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job4,time->" + System.currentTimeMillis());
        });

        //cf4和cf3都执行完成后，执行cf5，无入参，无返回值
        CompletableFuture cf5 = cf4.runAfterBoth(cf3, () -> {
            System.out.println(Thread.currentThread() + " start job5,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("cf5 do something");
            System.out.println(Thread.currentThread() + " exit job5,time->" + System.currentTimeMillis());
        });

        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("cf run result->" + cf.get());
        System.out.println("main thread start cf5.get(),time->" + System.currentTimeMillis());
        System.out.println("cf5 run result->" + cf5.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    /**
     * applyToEither / acceptEither / runAfterEither
     * applyToEither会将已经执行完成的任务的执行结果作为方法入参，并有返回值；
     * acceptEither同样将已经执行完成的任务的执行结果作为方法入参，但是没有返回值；
     * runAfterEither没有方法入参，也没有返回值。
     * 注意两个任务中只要有一个执行异常，则将该异常信息作为指定任务的执行结果
     *
     * @throws Exception
     */
    @Test
    public void applyToEitherAcceptEitherRunAfterEitherTest() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        });
        CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return 3.2;
        });
        //cf和cf2的异步任务都执行完成后，会将其执行结果作为方法入参传递给cf3,且有返回值
        CompletableFuture<Double> cf3 = cf.applyToEither(cf2, (result) -> {
            System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
            System.out.println("job3 param result->" + result);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
            return result;
        });

        //cf和cf2的异步任务都执行完成后，会将其执行结果作为方法入参传递给cf3,无返回值
        CompletableFuture cf4 = cf.acceptEither(cf2, (result) -> {
            System.out.println(Thread.currentThread() + " start job4,time->" + System.currentTimeMillis());
            System.out.println("job4 param result->" + result);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job4,time->" + System.currentTimeMillis());
        });

        //cf4和cf3都执行完成后，执行cf5，无入参，无返回值
        CompletableFuture cf5 = cf4.runAfterEither(cf3, () -> {
            System.out.println(Thread.currentThread() + " start job5,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("cf5 do something");
            System.out.println(Thread.currentThread() + " exit job5,time->" + System.currentTimeMillis());
        });

        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("cf run result->" + cf.get());
        System.out.println("main thread start cf5.get(),time->" + System.currentTimeMillis());
        System.out.println("cf5 run result->" + cf5.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    /**
     * allOf / anyOf
     *
     * @throws Exception
     */
    @Test
    public void allOfAnyOfTest() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        });
        CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return 3.2;
        });
        CompletableFuture<Double> cf3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1300);
            } catch (InterruptedException e) {
            }
            //            throw new RuntimeException("test");
            System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
            return 2.2;
        });
        //allof等待所有任务执行完成才执行cf4，如果有一个任务异常终止，则cf4.get时会抛出异常，都是正常执行，cf4.get返回null
        //anyOf是只有一个任务执行完成，无论是正常执行或者执行异常，都会执行cf4，cf4.get的结果就是已执行完成的任务的执行结果
        CompletableFuture cf4 = CompletableFuture.anyOf(cf, cf2, cf3).whenComplete((a, b) -> {
            if (b != null) {
                System.out.println("error stack trace->");
                b.printStackTrace();
            } else {
                System.out.println("run succ,result->" + a);
            }
        });

        System.out.println("main thread start cf4.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("cf4 run result->" + cf4.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    @Test
    public void test() throws Exception {
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        });
        CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return 3.2;
        });
        CompletableFuture<Double> cf3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1300);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
            return 2.2;
        });

        //        CompletableFuture.allOf(cf, cf2, cf3).join();
        //        System.out.println("等待结果");
        //        Double v = cf.get();
        //        Double v2 = cf2.get();
        //        Double v3 = cf3.get();
        //        System.out.println(v);
        //        System.out.println(v2);
        //        System.out.println(v3);

        List<CompletableFuture> futures = new ArrayList<>();
        futures.add(cf);
        futures.add(cf2);
        futures.add(cf3);
        CompletableFuture<Void> allFutures = CompletableFuture
            .allOf(futures.toArray(new CompletableFuture[futures.size()]));
        CompletableFuture<List<Object>> listCompletableFuture = allFutures.thenApply(v -> futures.stream().map(r -> {
            try {
                return r.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList()));
        System.out.println(listCompletableFuture.get());

        CompletableFuture<List<Object>> listCompletableFuture1 = listCompletableFuture
            .thenApply(list -> list.stream().filter(u -> Objects.isNull(u)).collect(Collectors.toList()));

        System.out.println(listCompletableFuture1.get());
    }

    @Test
    public void test01() {
        System.out.println("aaa");
        CompletableFuture.runAsync(() -> {
            System.out.println("xxxxxxxxxxxxxxxxxxxxx");
            int a = 1/0;
        }).whenComplete((aVoid, throwable) -> {
            if (throwable != null) {
                System.out.println(throwable.getMessage());
            }
        });
        System.out.println("bbb");
    }
}
