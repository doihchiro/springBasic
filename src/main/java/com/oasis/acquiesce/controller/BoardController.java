package com.oasis.acquiesce.controller;

import com.oasis.acquiesce.domain.*;
import com.oasis.acquiesce.service.BoardService;
import com.oasis.acquiesce.util.UpDownUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final UpDownUtil upDownUtil;

    //list
    //@GetMapping("/list")
    public void list(Model model) {
        log.info("list...................");

        List<BoardVO> list = boardService.list();

        log.info(list);

        model.addAttribute("list", list);
    }

    @GetMapping("/list")
    public void list(
            @ModelAttribute("cri") Criteria criteria,
            Model model) {
        log.info("list...................");
        log.info("criteria:" + criteria);

        List<BoardVO> list = boardService.getList(criteria);

        log.info(list);

        model.addAttribute("list", list);

        PageDTO pageDTO = new PageDTO(criteria, boardService.getTotal(criteria));

        model.addAttribute("pageMaker", pageDTO);

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/read/{bno}")
    public String read(
            @PathVariable("bno") Long bno,
            @ModelAttribute("cri") Criteria criteria,
            Model model) {

        log.info("bno: " + bno);

        BoardVO boardVO = boardService.get(bno);

        log.info("boardVO: " + boardVO);

        model.addAttribute("vo", boardVO);

        return "board/read";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/register")
    public void register() {

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/register")
    public String registerPost(
            BoardVO boardVO,
            @RequestParam(value = "files", required = false) MultipartFile[] files,
            RedirectAttributes rttr) {

        log.info("------upload------");
        log.info("files: " + Arrays.toString(files));

        List<Attach> attachList = upDownUtil.upload(files);

        boardVO.setAttachList(attachList);

        log.info("boardVO: " + boardVO);

        Long bno = boardService.register(boardVO);

        rttr.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }

    @PostMapping("/remove/{bno}")
    public String remove(
            @PathVariable("bno") Long bno,
            @RequestParam(value = "anos", required = false) Long[] anos,
            @RequestParam(value = "fullNames", required = false) String[] fullNames,
            @AuthenticationPrincipal Member member,
            RedirectAttributes rttr) {

        // 기존의 게시물을 조회해서 비교하고
        BoardVO boardVO = boardService.get(bno);

        if (boardVO == null) {
            return "redirect:/board/list";
        }

        if (!boardVO.getWriter().equals(member.getUid())) {
            throw new AccessDeniedException("NOT_OWN_MEMBER");
        }

        // 비교가 끝나면 변경해서 저장
        boardVO.setTitle("해당 글은 삭제 되었습니다.");
        boardVO.setContent("해당 글은 삭제 되었습니다.");
        boardVO.setDelFlag(true);

        log.info("boardVO: " + boardVO);

        boardService.modify(boardVO, anos);

        // 삭제할 파일들 삭제
        upDownUtil.deleteFiles(fullNames);

        rttr.addFlashAttribute("result", boardVO.getBno());

        return "redirect:/board/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{bno}")
    public String modify(
            @PathVariable("bno") Long bno,
            @ModelAttribute("cri") Criteria criteria,
            @AuthenticationPrincipal Member member,
            Model model) {

        log.info("bno: " + bno);

        BoardVO boardVO = boardService.get(bno);

        log.info("boardVO: " + boardVO);
        log.info("member: " + member);

        if (member != null) {
            if (!member.getUid().equals(boardVO.getWriter())) {
                throw new AccessDeniedException("NOT_OWN_MEMBER");
            }
        }

        model.addAttribute("vo", boardVO);

        return "board/modify";
    }

    @PreAuthorize("principal.username == #boardVO.writer")
    @PostMapping("/modify/{bno}")
    public String modify(
            @PathVariable("bno") Long bno,
            BoardVO boardVO,
            @RequestParam(value = "files", required = false) MultipartFile[] files,
            @RequestParam(value = "anos", required = false) Long[] anos,
            @RequestParam(value = "fullNames", required = false) String[] fullNames,
            RedirectAttributes rttr) {

        boardVO.setBno(bno);

        List<Attach> attachList = upDownUtil.upload(files);

        if (attachList != null && !attachList.isEmpty()) {
            boardVO.setAttachList(attachList);
        }

        log.info("boardVO: " + boardVO);

        boardService.modify(boardVO, anos);

        // 삭제할 파일들 삭제
        upDownUtil.deleteFiles(fullNames);

        //rttr.addFlashAttribute("result", boardVO.getBno());

        return "redirect:/board/read/{bno}";
    }

}

