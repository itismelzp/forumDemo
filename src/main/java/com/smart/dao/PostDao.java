package com.smart.dao;

import com.smart.domain.Post;
import org.springframework.stereotype.Repository;

/**
 * Created by lizhiping03 on 2018/9/12.
 */

@Repository
public class PostDao extends BaseDao<Post> {

    private static final String GET_PAGED_POSTS = "from post where topic.topicId = ? order by createTime desc";

    private static final String DELETE_TOPIC_POSTS = "delete from Post where topic.topicId = ?";

    public Page getPagePosts(int topicId, int pageNo, int pageSize) {
        return pagedQuery(GET_PAGED_POSTS, pageNo, pageSize, topicId);
    }

    public void deleteTopicPosts(int topicId) {
        getHibernateTemplate().bulkUpdate(DELETE_TOPIC_POSTS, topicId);
    }
}
