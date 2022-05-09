package com.record.calendar.controller;

import com.record.calendar.memberDto.MemberDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.record.calendar.calendarDto.CalendarDto;
import com.record.calendar.calendarService.CalendarService;

import java.util.logging.Logger;

@Controller
public class CalendarController {
	
	@Autowired
	CalendarService calendarService;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/calendar")
    public String main(@SessionAttribute(name="loginMember", required = false) String loginMember, Model model){

        if(loginMember == null || loginMember == ""){
            return "redirect:/";
        }

        model.addAttribute("member", loginMember);
        return "calendar";
    }
	
	@GetMapping("/workSave")
	@ResponseBody
	public int insertWork(CalendarDto calendarDto) {
		return calendarService.insertTodayWork(calendarDto);
	}
	
	@GetMapping("/getMyPlan")
	@ResponseBody
	public String getPlan(@RequestParam(value ="start") String start) {
		return calendarService.getAllPlan(start);
	}
	
	@GetMapping("/getTodayPlan")
	@ResponseBody
	public CalendarDto todayPlan(@RequestParam(value ="start") String start) {
		return calendarService.getSelectPlan(start);
	}
	
	@GetMapping("/deletePlan")
	@ResponseBody
	public int planDelete(@RequestParam(value ="start") String start) {
		return calendarService.deletePlan(start);
	}
}
