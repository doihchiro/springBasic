package com.oasis.acquiesce.mappers;

import com.oasis.acquiesce.domain.BoardVO;

import java.util.List;

public interface BoardMapper {

    List<BoardVO> getList();
    int insert(BoardVO boardVO);
    BoardVO select(Long bno);
    int update(BoardVO boardVO);
}
