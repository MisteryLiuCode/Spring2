package com.liu.mySpring.ioc;

//用于封装记录bean的信息[1、scope2,bean对应的class对象]
public class BeanDefination {
    private String scope;
    private Class aClass;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    @Override
    public String toString() {
        return "BeanDefination{" +
                "scope='" + scope + '\'' +
                ", aClass=" + aClass +
                '}';
    }
}
