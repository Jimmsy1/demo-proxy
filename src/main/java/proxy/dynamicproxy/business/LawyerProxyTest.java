package proxy.dynamicproxy.business;

public class LawyerProxyTest {

    public static void main(String[] args) {

        People people = new People("001","小明");
        /*ILawyer obj = (ILawyer) new LawyerDynamicProxy().getInstance(new AdviserImpl());
        obj.litigate(people);*/


        IUserInfo userInfo = (IUserInfo)new LawyerDynamicProxy().getInstance(new UserInfoImpl());
        userInfo.create(people);

    }
}
