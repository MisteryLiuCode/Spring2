package com.liu.spring.component;


import org.springframework.stereotype.Component;

@Component
public class UserDao {
    public void hi(){
        System.out.println("userDao的hi方法");
    }
}
