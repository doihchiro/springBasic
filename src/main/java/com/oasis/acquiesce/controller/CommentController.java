package com.oasis.acquiesce.controller;

import com.oasis.acquiesce.domain.Comment;
import com.oasis.acquiesce.domain.Criteria;
import com.oasis.acquiesce.domain.PageDTO;
import com.oasis.acquiesce.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Log4j2
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/list/{bno}")
    public Map<String,Object> listOfBoard(@PathVariable("bno") Long bno, Criteria criteria) {

        log.info("bno: {}", bno);
        log.info("criteria: {}", criteria);

        List<Comment> comments = commentService.getComments(criteria, bno);

        int totalComments = commentService.getTotalComments(criteria, bno);

        PageDTO pageDTO = new PageDTO(criteria, totalComments);

        return Map.of("comments", comments, "pageDTO", pageDTO);

    }
}
