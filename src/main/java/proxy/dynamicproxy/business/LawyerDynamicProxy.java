package proxy.dynamicproxy.business;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LawyerDynamicProxy implements InvocationHandler {

    private Object obj;

    public Object getInstance(Object obj){
        this.obj = obj;
        Class<?> clazz = obj.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces() , this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obj = null;
        try {
            start();        //开启事务
            obj = method.invoke(this.obj, args);
            end();          //结束事务
            return obj;
        }catch (Exception e){
            back();         //回滚事务

            e.printStackTrace();

        }
       return obj;
    }

    private void start() throws Exception{
        System.out.println("开启事务");
        /*System.out.println("接收到" + obj.getClass().getMethod("getUserName").invoke(obj) + "的诉讼");
        System.out.println("开始收集资料");*/
    }

    private void end(){
        System.out.println("处理完成");
        System.out.println("提交事务");
    }

    private void back(){
        System.out.println("回滚事务");
    }
}
