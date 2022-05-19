package com.record.calendar.controller;


import com.record.calendar.memberService.MemberService;
import com.record.calendar.memberServiceImp.MemberServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.record.calendar.calendarDto.CalendarDto;
import com.record.calendar.calendarService.CalendarService;
import java.util.Map;

@Controller
@Slf4j
public class CalendarController {
	
	@Autowired
	CalendarService calendarService;

    @Autowired
    MemberService memberService;

    @RequestMapping("/calendar")
    public String main(@SessionAttribute(name="loginMember", required = false) String loginMember, Model model){
        model.addAttribute("member", memberService.getMember(loginMember));
        return "calendar";
    }
	
	@PostMapping("/workSave")
	@ResponseBody
	public int insertWork(CalendarDto calendarDto, @SessionAttribute(name="loginMember", required = false) String loginMember) {
        calendarDto.setUser(loginMember);
		return calendarService.insertTodayWork(calendarDto);
	}
	
	@PostMapping("/getMyPlan")
	@ResponseBody
	public String getPlan(@RequestParam(value ="start") String start, @SessionAttribute(name="loginMember", required = false) String loginMember) {
		return calendarService.getAllPlan(loginMember);
	}
	
	@PostMapping("/getTodayPlan")
	@ResponseBody
	public CalendarDto todayPlan(@RequestParam(value ="start") String start, @SessionAttribute(name="loginMember", required = false) String loginMember) {
		return calendarService.getSelectPlan(start, loginMember);
	}
	
	@PostMapping("/deletePlan")
	@ResponseBody
	public int planDelete(@RequestParam(value ="start") String start, @SessionAttribute(name="loginMember", required = false) String loginMember) {
		return calendarService.deletePlan(start, loginMember);
	}

    @PostMapping("/select/pageNumber")
    @ResponseBody
    public String selectPage(@RequestParam int page, @SessionAttribute(name="loginMember", required = false) String loginMember){
        return calendarService.getSelectPagePlan(page, loginMember);
    }

    @PostMapping("/searchPlan")
    @ResponseBody
    public Map<String, Object> getSearchPlan(@RequestParam String search, @RequestParam String searchOption, @SessionAttribute(name="loginMember", required = false) String loginMember){
        return calendarService.getSearchPlan(search, searchOption, loginMember);
    }

    @PostMapping("/search/selectNumber")
    @ResponseBody
    public String searchSelectPage(@RequestParam int page,@RequestParam String search, @SessionAttribute(name="loginMember", required = false) String loginMember){
        return calendarService.getSearchSelectPage(page, search, loginMember);
    }
}
