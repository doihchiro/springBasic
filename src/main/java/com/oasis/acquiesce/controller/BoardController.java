package com.oasis.acquiesce.controller;

import com.oasis.acquiesce.domain.BoardVO;
import com.oasis.acquiesce.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //list
    @GetMapping("/list")
    public void list(Model model) {
        log.info("list...................");

        List<BoardVO> list = boardService.list();

        log.info(list);

        model.addAttribute("list", list);
    }


    @GetMapping("{job}/{bno}")
    public String read(
            @PathVariable("job") String job,
            @PathVariable("bno") Long bno,
            Model model) {

        log.info("job: " + job);
        log.info("bno: " + bno);

        if ( !(job.equals("read") || job.equals("modify")) ) {
           throw  new RuntimeException("Bad Request job");
        }

        BoardVO boardVO = boardService.get(bno);

        log.info("boardVO: " + boardVO);

        model.addAttribute("vo", boardVO);

        return "board/" + job;
    }

    @GetMapping("/register")
    public void register() {}

    @PostMapping("/register")
    public String registerPost(BoardVO boardVO, RedirectAttributes rttr) {

        log.info("boardVO: " + boardVO);

        Long bno = boardService.register(boardVO);

        rttr.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }


    @PostMapping("/remove/{bno}")
    public String remove(
            @PathVariable("bno") Long bno, RedirectAttributes rttr) {

        BoardVO boardVO = new BoardVO();
        boardVO.setBno(bno);
        boardVO.setTitle("해당 글은 삭제 되었습니다.");
        boardVO.setContent("해당 글은 삭제 되었습니다.");

        log.info("boardVO: " + boardVO);

        boardService.modify(boardVO);

        rttr.addFlashAttribute("result", boardVO.getBno());

        return "redirect:/board/list";
    }

    @PostMapping("/modify/{bno}")
    public String modify(
            @PathVariable("bno") Long bno, BoardVO boardVO) {

        boardVO.setBno(bno);

        log.info("boardVO: " + boardVO);

        boardService.modify(boardVO);

        return "redirect:/board/read/{bno}";
    }


//    @GetMapping("/modify/{bno}")
//    public String modify(@PathVariable("bno") Long bno, Model model) {
//
//        log.info("bno: " + bno);
//
//        BoardVO boardVO = boardService.get(bno);
//
//        log.info("boardVO: " + boardVO);
//
//        model.addAttribute("vo", boardVO);
//
//        return "board/modify";
//    }
}

