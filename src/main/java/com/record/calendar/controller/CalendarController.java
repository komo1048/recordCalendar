package com.record.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.record.calendar.calendarDto.CalendarDto;
import com.record.calendar.calendarService.CalendarService;

@Controller
public class CalendarController {
	
	@Autowired
	CalendarService calendarService;
	
	@RequestMapping("/")
	public String hello() {
		return "index.html";
	}
	
	@GetMapping("/workSave")
	public String insertWork(CalendarDto calendarDto) {
		calendarService.insertTodayWork(calendarDto);
		return "redirect:/";
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
}
