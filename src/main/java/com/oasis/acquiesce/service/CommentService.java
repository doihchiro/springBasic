package com.oasis.acquiesce.service;

import com.oasis.acquiesce.domain.Comment;
import com.oasis.acquiesce.domain.Criteria;
import com.oasis.acquiesce.mappers.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    public Long registerComment(Comment comment) {
        commentMapper.insert(comment);
        return comment.getRno();
    }

    public Comment getComment(Long rno) {
        return commentMapper.selectOne(rno);
    }

    public boolean modifyComment(Comment comment) {
        return commentMapper.updateOne(comment) == 1;
    }

    public boolean deleteComment(Long rno) {
        return commentMapper.deleteOne(rno) == 1;
    }

    public List<Comment> getComments(Criteria criteria, Long bno) {
        return commentMapper.getList(criteria, bno);
    }

    public int getTotalComments(Criteria criteria, Long bno) {
        return commentMapper.getTotal(criteria, bno);
    }
}
