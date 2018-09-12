package com.smart.dao;

import com.smart.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

/**
 * Created by lizhiping03 on 2018/9/12.
 */

@Repository
public class BoardDao extends BaseDao<Board> {

    private static final String GET_BOARD_NUM = "select count(f.boardId) from Board f";

    public long getBoardNum() {
        Iterator iterator = getHibernateTemplate().iterate(GET_BOARD_NUM);
        return (Long) iterator.next();
    }
}
