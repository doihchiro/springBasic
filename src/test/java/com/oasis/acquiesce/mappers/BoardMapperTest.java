package com.oasis.acquiesce.mappers;

import com.oasis.acquiesce.domain.BoardVO;
import com.oasis.acquiesce.domain.Criteria;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
class BoardMapperTest {

    @Autowired(required = false)
    BoardMapper boardMapper;

    @Test
    public void test() {
        log.info(boardMapper);
    }

    @Test
    public void testList() {
        boardMapper.getList().forEach(boardVO -> log.info(boardVO));
    }

    @Test
    public void testInsert() {
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("test");
        boardVO.setContent("test content");
        boardVO.setWriter("apple");

        log.info("COUNT: " + boardMapper.insert(boardVO));

        log.info("BNO: " + boardVO.getBno());
    }

    @Test
    public void testSelect() {
        Long bno = 7L;

        log.info(boardMapper.select(bno));
    }

    @Test
    public void testUpdate() {
        BoardVO updateBoardVO = new BoardVO();
        updateBoardVO.setBno(7L);
        updateBoardVO.setTitle("update test");
        updateBoardVO.setContent("update test content");

        int updateCount = boardMapper.update(updateBoardVO);

        log.info("update: " + updateCount);
    }

    @Test
    public void testPage() {
        Criteria criteria = new Criteria();

        criteria.setPageNum(3);
        // 1, 10
        List<BoardVO> list = boardMapper.getPage(criteria);

        list.forEach(boardVO -> log.info(boardVO));
    }
}