package proxy.dynamicproxy.gpproxy;

import proxy.staticproxy.Person;

import java.lang.reflect.Method;

public class GpMatchmakerProxy implements GPInvocationHandler{

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.person , args);
    }

    private Object person;

    public Object getInstance(Object person){
        this.person = person;
        Class<?> clazz = person.getClass();
        return GpProxy.newProxyInstance(new GpClassLoader() , clazz.getInterfaces() , this);
    }
}
