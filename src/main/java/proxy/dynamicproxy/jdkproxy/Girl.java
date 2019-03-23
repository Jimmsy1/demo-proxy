package proxy.dynamicproxy.jdkproxy;

import proxy.staticproxy.Person;

public class Girl implements Person {

    public void findLove() {
        System.out.println("要求1");
        System.out.println("要求2");
        System.out.println("要求2");
    }

    public void findSon() {
        System.out.println("找人要求1");
        System.out.println("找人要求2");
        System.out.println("找人要求2");
    }
}
