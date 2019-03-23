package proxy.dynamicproxy.business;

import java.util.List;

public interface IUserInfo {

    int create(People people);

    void delete(People people);

    int update(People people);

    List<?> queryAll();

}
