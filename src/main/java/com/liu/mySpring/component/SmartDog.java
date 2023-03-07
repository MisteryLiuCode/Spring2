package com.liu.mySpring.component;

import com.liu.mySpring.annotation.Component;
import com.liu.mySpring.processor.InitializingBean;

@Component(value = "smartDog")
public class SmartDog implements SmartAnimal, InitializingBean {
    @Override
    public Float getSum(Float a, Float b) {
//        传统方法,直接在每个方法上输出日志
//        System.out.println("日志-方法名-getSum-参数"+a+" "+b);
//        模拟异常通知
//        int i=10/0;
        float res=a+b;
        System.out.println("方法内执行结果"+res);
//        System.out.println("方法内部打印res="+res);
//        System.out.println("日志-方法名-getSum-结果res="+res);
        return res;
    }

    @Override
    public Float getSub(Float a, Float b) {
        System.out.println("SmartDog-getSub()执行");
        return a-b;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("SmartDog初始化方法被调用");
    }
}
