package com.smart.dao;

import com.smart.domain.Topic;
import org.springframework.stereotype.Repository;

/**
 * Created by lizhiping03 on 2018/9/12.
 */

@Repository
public class TopicDao extends BaseDao<Topic> {

    private static final String GET_BOARD_DIGEST_TOPICS = "from Topic t where t.board = ? and digest > 0 order by t.lastPost desc, digest desc";

    private static final String GET_PAGED_TOPICS = "from Topic where boardId = ? order by lastPost desc";

    private static final String QUERY_TOPIC_BY_TITILE = "from Topic where topicTitle like ? order by lastPost desc";

    public Page getBoardDigestTopics(int boardId, int pageNo, int pageSize) {
        return pagedQuery(GET_BOARD_DIGEST_TOPICS, pageNo, pageSize, boardId);
    }

    public Page getPagedTopics(int boardId, int pageNo, int pageSize) {
        return pagedQuery(GET_PAGED_TOPICS, pageNo, pageSize, boardId);
    }

    public Page queryTopicByTitle(String title, int pageNo, int pageSize) {
        return pagedQuery(QUERY_TOPIC_BY_TITILE, pageNo, pageSize, title);
    }
}
