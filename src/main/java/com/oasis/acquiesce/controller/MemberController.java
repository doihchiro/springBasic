package com.oasis.acquiesce.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("/login")
    public void loginPage(String error, String logout, Model model) {

        log.info("Custom Login Page...............");

        if (error != null) {
            log.info("-----------error---------------");
            log.info(error);
            log.info("-------------------------------");
        }
    }
}
