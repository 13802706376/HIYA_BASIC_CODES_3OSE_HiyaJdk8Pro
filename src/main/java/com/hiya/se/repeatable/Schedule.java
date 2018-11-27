package com.hiya.se.repeatable;

import java.lang.annotation.Repeatable;

/*
 * 这里有个使用@Repeatable( Schedules.class )的注解类Schedule，Schedules仅仅是Schedule注解的数组，
 * 但Java编译器并不想让程序员意识到Schedules的存在。这样对使用者而言，Target就拥有了两个Schedule注解，
 * 而不是1个Schedules注解。同时，反射相关的API提供了新的函数getAnnotationsByType()来返回重复注解的类型。
 */
@Repeatable(Schedules.class)
public @interface Schedule {
    int hour() default 0;
}