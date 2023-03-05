package com.liu.mySpring.processor;

/*
参考原生的spring容器，定义一个接口BeanPostProcessor
里面有两个方法，一个是在init方法前执行，一个是之后执行
这两个方法对所有spring容器的所有Bean生效，已经是切面编程的概念了。
 */
public interface BeanPostProcessor {
    /*
    在bean初始化方法前调用
     */
    public default Object postProcessBeforeInitialization(Object bean, String beanName){
        return bean;
    }
    /*
    在bean的初始化方法后调用
     */
    public default Object postProcessAfterInitialization(Object bean, String beanName){
        return bean;
    }
}
