package com.liu.mySpring.component;

import com.liu.mySpring.annotation.Component;
import com.liu.mySpring.processor.BeanPostProcessor;

/*
这是我们自定义的后置处理器方法
重写before和after方法
在spring容器中，需要注入到容器中才能工作
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("后置处理器postProcessBeforeInitialization Before调用 bean类型="+bean.getClass()
        +"bean的名称="+beanName);
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("后置处理器postProcessAfterInitialization after bean类型="+bean.getClass()
                +"bean的名称="+beanName);
        return bean;
    }
}
