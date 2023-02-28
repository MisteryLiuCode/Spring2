package com.liu.mySpring.ioc;



import com.liu.mySpring.annotation.Component;
import com.liu.mySpring.annotation.ComponentScan;

import java.io.File;
import java.net.URL;

public class SpringApplicationContext {
    //    接收配置文件的clss
    private Class cinfigClass;

    public SpringApplicationContext(Class cinfigClass) {
        this.cinfigClass = cinfigClass;
//        获取要扫描的包是什么
//        得到配置类的@component注解
        ComponentScan componentScan = (ComponentScan) this.cinfigClass.getDeclaredAnnotation(ComponentScan.class);
//        获取注解值
        String path = componentScan.value();
        System.out.println("要扫描的包=" + path);
//        获取扫描包下的所有class文件，也就是说要创建的对象
//        得到类的加载器
        ClassLoader classLoader = SpringApplicationContext.class.getClassLoader();
        //通过类的加载器获取到要扫描包的资源url
//        因为路径是/，而path是以.分割，需要进行一个替换
        path = path.replace(".", "/");
//        URL resource = classLoader.getResource(path);
        URL resource = classLoader.getResource(path);
        System.out.println("resource=" + resource);
//        将要加载的资源（.class）路径下的文件进行遍历
        File file = new File(resource.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
//                利用反射创建对象
                String fileAbsolutePath = f.getAbsolutePath();
//                因为后面以.class分割，所以只处理.class文件
                if (fileAbsolutePath.endsWith(".class")) {
//                截取字符串，获取全类名
                    String className = fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("/") + 1, fileAbsolutePath.indexOf(".class"));
                    System.out.println("className:" + className);
//                拼接全路径
                    String classFullName = path.replace("/", ".") + "." + className;
                    System.out.println("classFullName:" + classFullName);
//                进行过滤，看是否有注入对象的注解，是否要注入到容器中
                    try {
//                        这时就得到了该类的class对象
                        Class<?> aClass = classLoader.loadClass(classFullName);
//                        判断是否有注入对象的注解
                        if (aClass.isAnnotationPresent(Component.class)) {
//                            模拟如果是component注解，设置value值，把对象id设置为value
                                System.out.println("这是一个springBean="+aClass+"类名："+className);
                        }else {
                            System.out.println("这不是一个springBean="+aClass+"类名："+aClass);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("===============");
            }
        }
    }

    public Object getBean(String className) {
        return null;
    }
}
