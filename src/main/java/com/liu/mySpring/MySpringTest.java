package com.liu.mySpring;


import com.liu.mySpring.ioc.SpringApplicationContext;
import com.liu.mySpring.ioc.SpringConfig;

/**
 * Hello world!
 *
 */
public class MySpringTest
{
    public static void main( String[] args ) {
        new SpringApplicationContext(SpringConfig.class);


    }
}
