package com.liu.mySpring.component;


import com.liu.mySpring.annotation.Component;

@Component(value = "monsterDao")
public class MonsterDao {
    //验证依赖是否注入成功
    public void hi(){
        System.out.println("monsterDao-hi()被调用");
    }
}
