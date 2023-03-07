package com.liu.mySpring.component;

/*
直接当做切面类来使用
 */
public class SmartAnimalAspect {

    public static void showBeginLog(){
        System.out.println("前置通知..");
    }

    public static void showSuccessLog(){
        System.out.println("返回通知..");
    }
}
