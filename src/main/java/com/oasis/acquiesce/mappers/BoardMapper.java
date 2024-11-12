package com.oasis.acquiesce.mappers;

import com.oasis.acquiesce.domain.BoardVO;
import com.oasis.acquiesce.domain.Criteria;

import java.util.List;

public interface BoardMapper {

    List<BoardVO> getList();

    // paging 처리
    List<BoardVO> getPage(Criteria criteria);
    int getTotal(Criteria criteria);

    // INSERT, UPDATE, DELETE: 영향을 받은 행 수를 int로 반환.
    // SELECT: 결과 집합인 ResultSet을 반환.
    int insert(BoardVO boardVO);
    BoardVO select(Long bno);
    int update(BoardVO boardVO);
}
