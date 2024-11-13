package com.oasis.acquiesce.mappers;

import com.oasis.acquiesce.domain.Comment;

public interface CommentMapper {
    int insert(Comment comment);
    Comment selectOne(Long rno);
    int deleteOne(Long rno);
    int updateOne(Comment comment);
}
