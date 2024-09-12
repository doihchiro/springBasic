package com.oasis.acquiesce.mappers;

import com.oasis.acquiesce.domain.BoardVO;
import com.oasis.acquiesce.domain.Criteria;

import java.util.List;

public interface BoardMapper {

    List<BoardVO> getList();

    // paging 처리
    List<BoardVO> getPage(Criteria criteria);
    int getTotal(Criteria criteria);

    int insert(BoardVO boardVO);
    BoardVO select(Long bno);
    int update(BoardVO boardVO);
}
