package com.record.calendar.calendarServiceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.record.calendar.calendarDao.CalendarDao;
import com.record.calendar.calendarDto.CalendarDto;
import com.record.calendar.calendarService.CalendarService;


import sun.applet.Main;

@Service
public class CalendarServiceImp implements CalendarService{

	@Autowired
	CalendarDao calendarDao;
	
	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	@Override
	public int insertTodayWork(CalendarDto calendarDto) {
		try {
			return calendarDao.insertTodayWork(calendarDto);
		} catch (Exception e) {
			logger.warn("데이터가 이미 존재합니다.");
		}
		return 0;
	}

	@Override
	public String getAllPlan(String start) {
		Gson gson = new Gson(); 
		return gson.toJson(calendarDao.getAllPlanList());
	}

	@Override
	public CalendarDto getSelectPlan(String start) {
		return calendarDao.getSelectPlan(start);
	}

	@Override
	public int deletePlan(String start) {
		return calendarDao.deletePlan(start);
	}
}
