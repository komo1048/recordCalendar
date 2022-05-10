package com.record.calendar.memberController;

import com.record.calendar.memberDto.MemberDto;
import com.record.calendar.memberService.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/member/register")
    @ResponseBody
    public int register(MemberDto memberDto){
        return memberService.register(memberDto);
    }

    @RequestMapping("/checkId")
    @ResponseBody
    public int idCheck(MemberDto memberDto){
        return memberService.checkId(memberDto);
    }

    @PostMapping("/member/login")
    @ResponseBody
    public int login(MemberDto memberDto, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", memberDto.getId());
        return memberService.login(memberDto);
    }

    @GetMapping("/member/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }
}
