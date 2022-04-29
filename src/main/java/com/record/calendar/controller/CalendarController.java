package com.record.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		return "/";
	}

}
