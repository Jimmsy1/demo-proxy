package proxy.dynamicproxy.business;

import java.util.List;

public class UserInfoImpl implements IUserInfo {
    @Override
    public int create(People people) {
        System.out.println("创建对象");
        return 0;
    }

    @Override
    public void delete(People people) {
        System.out.println("删除对象");
    }

    @Override
    public int update(People people) {
        System.out.println("修改对象");
        return 0;
    }

    @Override
    public List<?> queryAll() {
        System.out.println("查询对象");
        return null;
    }
}
