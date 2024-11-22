package com.oasis.acquiesce.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/all")
    public void all() {
        log.info("All............");
    }

    @GetMapping("/manager")
    public void manager() {
        log.info("Manager............");
    }

    @GetMapping("/admin")
    public void admin() {
        log.info("Admin............");
    }
}
