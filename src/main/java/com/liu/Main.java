package com.liu;

import com.liu.spring.aop.SmartAnimal;
import com.liu.spring.component.UserAction;
import com.liu.spring.component.UserDao;
import com.liu.spring.component.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        测试看看是否可以得到spring容器中的bean，同时看看依赖是否注入
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        UserAction userAction = ioc.getBean("userAction", UserAction.class);
        UserAction userAction2 = ioc.getBean("userAction", UserAction.class);
//        对象是否是一个
        System.out.println("userAction="+userAction);
        System.out.println("userAction2="+userAction2);

        UserDao userDao = ioc.getBean("userDao", UserDao.class);
        System.out.println("userDao="+userDao);

        UserService userService = ioc.getBean("userService", UserService.class);
        System.out.println("userService="+userService);

//        测试依赖注入是否成功
        userService.m1();

        //测试aop
        SmartAnimal smartAnimal = ioc.getBean(SmartAnimal.class);
        smartAnimal.getSum(1F,1F);
    }
}