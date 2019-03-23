package proxy.dynamicproxy.jdkproxy;

import proxy.staticproxy.Person;
import sun.misc.ProxyGenerator;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class JDKProxyTest {

    public static void main(String[] args) {

        try {
            Person g = (Person) new JdkMatchmakerProxy().getInstance(new Girl());
            g.findSon();

            byte[] bytes = ProxyGenerator.generateProxyClass("$proxy0" , new Class[]{Person.class});
            FileOutputStream outputStream = new FileOutputStream("D://$proxy0.class");
            outputStream.write(bytes);
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
