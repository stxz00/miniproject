package org.handmade.miniproject.member.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @ResponseBody
    @GetMapping("/main")
    public String main(Principal principal, HttpServletRequest request) throws Exception{
        log.info("메인페이지로");
        System.out.println(principal.getName());

        HttpSession session =request.getSession();
        session.setAttribute("username",principal.getName());

        String username = (String) session.getAttribute("username");
        return username; //"redirect:http://localhost:3000";
    }





}
