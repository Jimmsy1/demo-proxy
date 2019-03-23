package proxy.dynamicproxy.gpproxy;

import proxy.dynamicproxy.jdkproxy.Girl;
import proxy.staticproxy.Person;

import java.lang.reflect.Method;

public class GpProxyTest {

    public static void main(String[] args) {
        try {
            /*Person per = (Person) new GpMatchmakerProxy().getInstance(new Girl());
            per.findLove();*/

            Object obj = new GpMatchmakerProxy().getInstance(new Girl());
            Method me = obj.getClass().getMethod("findLove" , null);
            me.invoke(obj , null);

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
