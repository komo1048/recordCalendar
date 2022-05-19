package com.record.calendar.memberController;

import com.record.calendar.calendarDto.CalendarDto;
import com.record.calendar.calendarService.CalendarService;
import com.record.calendar.memberDto.MemberDto;
import com.record.calendar.memberService.MemberService;
import com.record.calendar.paging.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    MemberService memberService;

    @Autowired
    CalendarService calendarService;

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

    @RequestMapping("/forgot")
    public String forgotPassword(){
        return "findPassword";
    }

    @PostMapping("/updateTempPwd")
    @ResponseBody
    public int updateTempPassword(MemberDto memberDto) {
        return memberService.updateTempPassword(memberDto);
    }

    @GetMapping("/profile")
    public String profile(@SessionAttribute(name="loginMember", required = false) String loginMember, Model model, @ModelAttribute("criteria")Criteria criteria){

        MemberDto memberDto = memberService.getMember(loginMember);
        List<CalendarDto> calendarDto = calendarService.getPagePlan(criteria,loginMember);
        int pageNumber = calendarService.getPageNumber(criteria,loginMember);

        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("member", memberDto);
        model.addAttribute("calendar", calendarDto);

        return "profile";
    }
}
