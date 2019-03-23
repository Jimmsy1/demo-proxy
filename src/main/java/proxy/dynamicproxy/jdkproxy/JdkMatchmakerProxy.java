package proxy.dynamicproxy.jdkproxy;

import proxy.staticproxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkMatchmakerProxy implements InvocationHandler {

    private Person person;

    public Object getInstance(Person person)throws Exception{
        this.person = person;
        Class<?> clazz = person.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader() , clazz.getInterfaces() , this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object object = method.invoke(this.person , args);
        after();
        return object;
    }

    private void before(){
        System.out.println("我是媒婆，我要给你找对象了，现在已经确认你的需求：");
        System.out.println("开始物色");
    }

    private void after(){
        System.out.println("满意吗?");
    }
}
