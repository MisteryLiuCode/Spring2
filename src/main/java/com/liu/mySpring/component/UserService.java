package com.liu.mySpring.component;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
public class UserService {
    //定义一个属性
    //完成依赖注入，spring的方式是@Autowired或者@Resource
    @Resource
    private UserDao userDao;
    public void m1(){
        userDao.hi();
    }

//    这是初始化方法，@PostConstruct指定这个方法为初始化方法
    @PostConstruct
    public void init(){
        System.out.println("UserService-init()");
    }
}
