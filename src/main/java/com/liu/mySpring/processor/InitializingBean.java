package com.liu.mySpring.processor;


/*
根据原生spring，自己定义了一个这个方法，用于当做init方法，为bean的后置处理器做铺垫。
接口里面的方法afterPropertiesSet，在bean的setter后执行，代替原来的init方法
当一个bean实现了这个接口后，就实现afterPropertiesSet()，这个方法就是初始化方法。
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
