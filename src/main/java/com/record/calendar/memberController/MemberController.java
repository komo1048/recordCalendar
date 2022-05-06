package com.record.calendar.memberController;

import com.record.calendar.memberDto.MemberDto;
import com.record.calendar.memberService.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/")
    public String loginPage(){
        return "login.html";
    }

    @GetMapping("/member/register")
    @ResponseBody
    public int register(MemberDto memberDto){
        return memberService.register(memberDto);
    }

    @RequestMapping("/checkId")
    @ResponseBody
    public int idCheck(MemberDto memberDto){
        return memberService.checkId(memberDto);
    }
}
