package com.liu.mySpring.component;

import com.liu.mySpring.annotation.Component;
import com.liu.mySpring.processor.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
        //实现aop机制，返回代理对象,假设smartDog需要处理
        //先死后活
        if ("smartDog".equals(beanName)){
//            使用jdk的动态代理，返回该bean的代理对象
            Object instance = Proxy.newProxyInstance(MyBeanPostProcessor.class.getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("method="+method.getName());
                    Object invoke;
                    //假如前置通知+处理的方法是getSum
                    if ("getSum".equals(method.getName())){
                        SmartAnimalAspect.showBeginLog();
//                        执行目标方法
                        invoke = method.invoke(bean, args);
//                        执行后置方法
                        SmartAnimalAspect.showSuccessLog();
                    }
                    else {
//                        如果不是切面类的目标方法，就直接执行目标方法
                        invoke = method.invoke(bean, args);
                    }
                    return invoke;
                }
            });
            //如果是aop的执行对象，那么返回代理对象
            return instance;
        }
//        不需要aop，返回原生对象
        return bean;
    }
}
