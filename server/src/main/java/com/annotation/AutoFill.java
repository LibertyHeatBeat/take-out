package com.annotation;

import com.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//目标：本注解用于方法上
@Retention(RetentionPolicy.RUNTIME)//运行反射时可以发现此注解
public @interface AutoFill {
    OperationType value();
}
