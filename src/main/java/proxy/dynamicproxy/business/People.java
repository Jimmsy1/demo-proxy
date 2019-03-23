package proxy.dynamicproxy.business;

public class People {

    private String userNo;

    private String userName;

    public People(String userNo, String userName) {
        this.userNo = userNo;
        this.userName = userName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
