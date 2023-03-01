package com.liu.mySpring.component;


import com.liu.mySpring.annotation.Component;
import com.liu.mySpring.annotation.Scope;

//通过注解注入到我们自己的spring容器中
//如果指定了value，将注入时使用我们自己的value，不指定将使用类型首字母小写
@Component(value = "monsterService")
@Scope(value = "prototype")
public class MonsterService {
}
