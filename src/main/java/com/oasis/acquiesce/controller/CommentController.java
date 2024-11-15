package com.oasis.acquiesce.controller;

import com.oasis.acquiesce.domain.Comment;
import com.oasis.acquiesce.domain.Criteria;
import com.oasis.acquiesce.domain.PageDTO;
import com.oasis.acquiesce.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Log4j2
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/register")
    public Map<String, Long> register(@RequestBody Comment comment) {
        log.info("comment: {}", comment);
        Long rno = commentService.registerComment(comment);
        return Map.of("RNO", rno);
    }

    @GetMapping("{rno}")
    public Comment get(@PathVariable("rno") Long rno) {
        return commentService.getComment(rno);
    }

    @DeleteMapping("{rno}")
    public Map<String, Boolean> delete(@PathVariable("rno") Long rno) {
        log.info("delete: {}", rno);
        boolean result = commentService.deleteComment(rno);
        return Map.of("Result", result);
    }

    @PutMapping("{rno}")
    public Map<String, Boolean> modify(@PathVariable("rno") Long rno, @RequestBody Comment comment) {
        log.info("update: {}", rno);
        comment.setRno(rno);
        boolean result = commentService.modifyComment(comment);
        return Map.of("Result", result);
    }

    @GetMapping("/list/{bno}")
    public Map<String,Object> listOfBoard(@PathVariable("bno") Long bno, Criteria criteria) {

        log.info("bno: {}", bno);
        log.info("criteria: {}", criteria);

        List<Comment> comments = commentService.getComments(criteria, bno);

        int totalComments = commentService.getTotalComments(criteria, bno);

        PageDTO pageDTO = new PageDTO(criteria, totalComments);

        Map<String,Object> map = new HashMap<>();
        map.put("comments", comments);
        map.put("pageDTO", pageDTO);

        return map;

    }
}
