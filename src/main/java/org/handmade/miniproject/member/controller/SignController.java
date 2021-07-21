package org.handmade.miniproject.member.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@ComponentScan
@Log4j2
public class SignController {

    //로그인
    @GetMapping("/signin")
    public String signin() {
        log.info("접근근");
        return "signin";
    }

    @GetMapping("/main")
    public String main(Principal principal){
        log.info("메인페이지로");
        System.out.println(principal.getName());
        return "redirect:http://192.168.1.35:3000";
    }




}
