package com.oasis.acquiesce.service;

import com.oasis.acquiesce.domain.Attach;
import com.oasis.acquiesce.domain.BoardVO;
import com.oasis.acquiesce.domain.Criteria;
import com.oasis.acquiesce.mappers.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardMapper boardMapper;

    public Long register(BoardVO boardVO) {

        log.info("--------------" + boardVO);

        boardMapper.insert(boardVO);

        Long bno = boardVO.getBno();

        List<Attach> attachList = boardVO.getAttachList();

        if (attachList != null && !attachList.isEmpty()) {
            for (Attach attach : attachList) {
                attach.setBno(bno);
                boardMapper.insertAttach(attach);
            }
        }

        return bno;
    }

    public List<BoardVO> list() {
        return boardMapper.getList();
    }

    //paging
    public List<BoardVO> getList(Criteria criteria) {
        return boardMapper.getPage(criteria);
    }

    public int getTotal(Criteria criteria) {
        return boardMapper.getTotal(criteria);
    }

    public BoardVO get(Long bno) {
        return boardMapper.select(bno);
    }

    public boolean modify(BoardVO boardVO) {
        return boardMapper.update(boardVO) == 1;
    }

    public boolean remove(Long bno) {
        return true;
    }
}
