package com.smart.dao;

import com.smart.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lizhiping03 on 2018/9/12.
 */

@Repository
public class UserDao extends BaseDao<User> {

    private static final String GET_USER_BY_USERNAME = "from User u where u.userName = ?";

    private static final String QUERY_USER_BY_USERNAME = "from User u where u.userName like ?";

    public User getUserByUserName(String userName) {
        List<User> users = (List<User>) getHibernateTemplate().find(GET_USER_BY_USERNAME, userName);
        if (users.size() == 0) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public List<User> queryUserByName(String userNaem) {
        return (List<User>) getHibernateTemplate().find(QUERY_USER_BY_USERNAME, userNaem + "%");
    }
}
