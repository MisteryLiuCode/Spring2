package com.liu.mySpring;


import com.liu.mySpring.component.MonsterService;
import com.liu.mySpring.component.SmartAnimal;
import com.liu.mySpring.component.SmartDog;
import com.liu.mySpring.ioc.SpringApplicationContext;
import com.liu.mySpring.ioc.SpringConfig;

/**
 * Hello world!
 *
 */
public class MySpringTest
{
    public static void main( String[] args ) {
        SpringApplicationContext ioc = new SpringApplicationContext(SpringConfig.class);
//        Object monsterDao1= ioc.getBean("monsterDao");
//        Object monsterDao2 = ioc.getBean("monsterDao");
//
//        System.out.println("monsterDao1："+monsterDao1);
//        System.out.println("monsterDao2："+monsterDao2);
//        System.out.println("====测试多例====");
//        Object monsterService1 = ioc.getBean("monsterService");
//        Object monsterService2= ioc.getBean("monsterService");
//        System.out.println("monsterService1："+monsterService1);
//        System.out.println("monsterService2："+monsterService2);
//        System.out.println("ok");

//        测试依赖注入
//        MonsterService monsterService = (MonsterService) ioc.getBean("monsterService");
//        monsterService.m1();

        SmartAnimal smartDog = (SmartAnimal)ioc.getBean("smartDog");
        System.out.println("smartDog="+smartDog.getClass());
    }
}
