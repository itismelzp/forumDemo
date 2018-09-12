package com.smart.dao;

import com.smart.domain.LoginLog;
import org.springframework.stereotype.Repository;

/**
 * Created by lizhiping03 on 2018/9/12.
 */

@Repository
public class LoginLogDao extends BaseDao<LoginLog> {
    public void save(LoginLog loginLog) {
        this.getHibernateTemplate().save(loginLog);
    }
}
