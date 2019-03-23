package proxy.dynamicproxy.business;

public class AdviserImpl implements ILawyer {

    @Override
    public void litigate(People people) {
        System.out.println("开司始打官");
    }
}
