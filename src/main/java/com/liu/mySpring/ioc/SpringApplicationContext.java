package com.liu.mySpring.ioc;


import com.liu.mySpring.annotation.Component;
import com.liu.mySpring.annotation.ComponentScan;
import com.liu.mySpring.annotation.Scope;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

public class SpringApplicationContext {
    //    接收配置文件的clss
    private Class cinfigClass;

    //定义属性BeanDefination
    private ConcurrentHashMap<String, BeanDefination> beanDefinationConcurrentMap = new ConcurrentHashMap<>();
    //定义属性SingletonObjects,存放单例对象
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    public SpringApplicationContext(Class cinfigClass) {
        //完成扫描
        beanDefinationByScan(cinfigClass);
        //通过beanDefinitionMap，初始化singletonObjects单例池
        System.out.println("beanDefinationMap" + beanDefinationConcurrentMap);
        //拿到所有key，也就是beanName
        Enumeration<String> keys = beanDefinationConcurrentMap.keys();
        while (keys.hasMoreElements()){
            String beanName = keys.nextElement();
//            通过beanName得到对应的beanDefination
            BeanDefination beanDefination = beanDefinationConcurrentMap.get(beanName);
//            判断该bean是singleton还是prototype
            if ("singleton".equalsIgnoreCase(beanDefination.getScope())){
//                将该bean实例放入singletonObjects
//                调用创建bean的方法，直接创建bean
                Object bean = creatBean(beanDefination);
                singletonObjects.put(beanName,bean);
            }

        }

    }

    public void beanDefinationByScan(Class cinfigClass) {
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
                            //这里将bean的信息封装到BeanDefination放入到BeanDefinationMap
                            BeanDefination beanDefination = new BeanDefination();
                            Component componentAnnotion = aClass.getDeclaredAnnotation(Component.class);
                            //获取Component
                            String beanName = componentAnnotion.value();
                            if (beanName.equals("")) {//如果没有
                                //将该类的类名首字母小写作为beanName
//                                StringUtils.uncapitalize首字母小写
                                beanName = StringUtils.uncapitalize(className);
                            }
                            beanDefination.setaClass(aClass);
                            if (aClass.isAnnotationPresent(Scope.class)) {
                                //如果配置了scope
                                Scope scopeAnnotaion = aClass.getDeclaredAnnotation(Scope.class);
                                beanDefination.setScope(scopeAnnotaion.value());
                            } else {
                                beanDefination.setScope("singleton");
                            }
                            System.out.println("这是一个springBean=" + aClass + "类名：" + className);
                            //将beanDefination对象放入到map中
                            beanDefinationConcurrentMap.put(beanName, beanDefination);
                        } else {
                            System.out.println("这不是一个springBean=" + aClass + "类名：" + aClass);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("===============");
            }
        }
    }

//    完成creatBean(BeanDefination beanDefination)，用于创建对象
//    容器初始化完就初始化单例池
    private Object creatBean(BeanDefination beanDefination){
        //得到bean的clazz对象
        Class aClass = beanDefination.getaClass();
        //使用反射创建bean
        try {
            Object instance = aClass.getDeclaredConstructor().newInstance();
            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //反射失败返回null
        return null;
    }

    public Object getBean(String className) {
        if (beanDefinationConcurrentMap.containsKey(className)){
//        拿到bean的信息
        BeanDefination beanDefination = beanDefinationConcurrentMap.get(className);
        if ("singleton".equalsIgnoreCase(beanDefination.getScope())){
            return singletonObjects.get(className);
        }
        //creatBean()创建bean
        return creatBean(beanDefination);
        } else {
            //不存在，抛异常
            throw new NullPointerException("没有该bean");
        }
    }
}
