package com.liu.mySpring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//作用于类上，属性上
@Target({TYPE, FIELD})
@Retention(RUNTIME)
public @interface Autowired {
    boolean required() default true;
}
