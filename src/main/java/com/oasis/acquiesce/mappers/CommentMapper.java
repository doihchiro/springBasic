package com.oasis.acquiesce.mappers;

import com.oasis.acquiesce.domain.Comment;
import com.oasis.acquiesce.domain.Criteria;
import org.apache.ibatis.annotations.Param;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

public interface CommentMapper {
    int insert(Comment comment);
    Comment selectOne(Long rno);
    int deleteOne(Long rno);
    int updateOne(Comment comment);

    List<Comment> getList(@Param("cri") Criteria cri, @Param("bno") Long bno);
    int getTotal(@Param("cri") Criteria cri, @Param("bno") Long bno);
}
