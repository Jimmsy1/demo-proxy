package proxy.dynamicproxy.gpproxy;


import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class GpProxy {

    private static final String ln = "\r\n";

    public static Object newProxyInstance(GpClassLoader loader,
                                          Class<?>[] interfaces,
                                          GPInvocationHandler h){
        try{
            //1、第一步动态生成源代码
            String str = createSrc(interfaces);

            //2、通过java输出到磁盘
            String clazzPath = GpProxy.class.getResource("").getPath();
            File f = new File(clazzPath + "$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(str);
            fw.flush();
            fw.close();

            //3、把生成的java编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
            Iterable iterable = manager.getJavaFileObjects(f);

            //4、将.class加载到jvm来
            JavaCompiler.CompilationTask task = compiler.getTask(null,manager,null , null ,null,iterable);
            task.call();
            manager.close();

            //5、返回字节码重组以后的新的代理对象类
            Class proxyClass = loader.findClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(GPInvocationHandler.class);

            //6、把Handler传入
            return c.newInstance(h);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private static String createSrc(Class<?>[] interfaces){

        //用代码写代码
        StringBuffer sb = new StringBuffer();
        sb.append("package proxy.dynamicproxy.gpproxy;" + ln);
        sb.append("import proxy.staticproxy.Person;" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{"+ln);

        sb.append("GPInvocationHandler h;" + ln);

        sb.append("public $Proxy0(GPInvocationHandler h){ " + ln);
            sb.append("this.h = h; " + ln);
        sb.append("}"+ln);

        for (Method md: interfaces[0].getMethods()) {
            sb.append("public " + md.getReturnType() + " " + md.getName() + "() {"+ln);
                sb.append("try{"+ln);
                        sb.append("Method m = "+interfaces[0].getName() + ".class.getMethod(\""+md.getName()+"\", new Class[]{});"+ln);
                        sb.append(" this.h.invoke(this, m , null); " + ln);
                sb.append("}catch (Throwable e){"+ln);
                    sb.append("e.printStackTrace();"+ln);
                sb.append("}"+ln);
            sb.append("}"+ln);
        }

        sb.append("}");

        return sb.toString();
    }
}
