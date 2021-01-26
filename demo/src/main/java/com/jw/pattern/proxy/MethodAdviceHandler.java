package com.jw.pattern.proxy;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * guoyy com.jw.pattern.proxy
 * <p>
 * 方法增强处理器
 * <p>
 * 定义方法增强处理器
 * 我们先定义出 ”代理“ 的抽象：方法增强处理器 MethodAdviceHandler。
 * 之后我们定义的每一个注解，都绑定一个对应的 MethodAdviceHandler 的实现类，
 * 当目标方法被代理时，由对应的 MethodAdviceHandler 的实现类来处理该方法的代理访问。
 *
 * @param <R> 目标方法返回值的类型
 * @Description: com.jw.pattern.proxy.MethodAdviceHandler
 * @Author: guoyiyong/james
 * @Date: 2021-01-23 18:22
 * @Version: 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public interface MethodAdviceHandler<R> {

    /**
     * 目标方法执行之前的判断，判断目标方法是否允许执行。默认返回 true，即 默认允许执行
     *
     * @param point 目标方法的连接点
     * @return 返回 true 则表示允许调用目标方法；返回 false 则表示禁止调用目标方法。
     * 当返回 false 时，此时会先调用 getOnForbid 方法获得被禁止执行时的返回值，然后
     * 调用 onComplete 方法结束切面
     */
    default boolean onBefore(ProceedingJoinPoint point) { return true; }

    /**
     * 禁止调用目标方法时（即 onBefore 返回 false），执行该方法获得返回值，默认返回 null
     *
     * @param point 目标方法的连接点
     * @return 禁止调用目标方法时的返回值
     */
    default R getOnForbid(ProceedingJoinPoint point) { return null; }

    /**
     * 目标方法抛出异常时，执行的动作
     *
     * @param point 目标方法的连接点
     * @param e     抛出的异常
     */
    void onThrow(ProceedingJoinPoint point, Throwable e);

    /**
     * 获得抛出异常时的返回值，默认返回 null
     *
     * @param point 目标方法的连接点
     * @param e     抛出的异常
     * @return 抛出异常时的返回值
     */
    default R getOnThrow(ProceedingJoinPoint point, Throwable e) { return null; }

    /**
     * 目标方法完成时，执行的动作
     *
     * @param point     目标方法的连接点
     * @param startTime 执行的开始时间
     * @param permitted 目标方法是否被允许执行
     * @param thrown    目标方法执行时是否抛出异常
     * @param result    执行获得的结果
     */
    default void onComplete(ProceedingJoinPoint point, long startTime, boolean permitted, boolean thrown, Object result) { }
}
