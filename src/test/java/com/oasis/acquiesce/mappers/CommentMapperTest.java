package com.oasis.acquiesce.mappers;

import com.oasis.acquiesce.domain.Comment;
import com.oasis.acquiesce.domain.Criteria;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
class CommentMapperTest {

    @Autowired(required = false)
    CommentMapper commentMapper;

    @Test
    void test() {
        log.info(commentMapper);
    }

    @Test
    void testInsert() {
        Long bno = 555L;

        for (int i = 1; i <= 260; i++) {
            Comment comment = Comment.builder()
                    .bno(bno)
                    .commentText("test comment..." + i)
                    .commenter("loopy")
                    .build();
            int insert = commentMapper.insert(comment);
            log.info(insert);
        }
    }

    @Test
    void testSelectOne() {
        Long rno = 7L;

        log.info(commentMapper.selectOne(rno));
    }

    @Test
    void testDeleteOne() {
        log.info(commentMapper.deleteOne(15L));
    }

    @Test
    void testUpdateOne() {
        Comment comment = Comment.builder().rno(1L).commentText("update...1").build();
        log.info(commentMapper.updateOne(comment));
    }


    @Test
    void testGetList() {
        Criteria criteria = new Criteria();

        commentMapper.getList(criteria, 555L).forEach(comment -> log.info(comment));
    }

    @Test
    void testGetTotal() {
        Criteria criteria = new Criteria();

        log.info(commentMapper.getTotal(criteria, 553L));
    }
}












