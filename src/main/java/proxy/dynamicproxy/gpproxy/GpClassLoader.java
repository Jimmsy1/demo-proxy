package proxy.dynamicproxy.gpproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class GpClassLoader extends ClassLoader {

    private File classPathFile;

    public GpClassLoader(){
        String clazzPath = GpClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(clazzPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {


        String clazzName = GpClassLoader.class.getPackage().getName() + "." + name;
        if(classPathFile != null){
            File clazzFile = new File(classPathFile , name.replaceAll("\\.","/" ) + ".class");
            if(clazzFile.exists()){
                FileInputStream in = null;
                ByteArrayOutputStream out = null;
                try{
                    in = new FileInputStream(clazzFile);
                    out = new ByteArrayOutputStream();
                    byte [] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1){
                        out.write(buff,0,len);
                    }

                    return defineClass(clazzName,out.toByteArray(),0,out.size());

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return null;

    }
}
