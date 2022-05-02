package com.record.calendar.calendarServiceImp;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.record.calendar.calendarDao.CalendarDao;
import com.record.calendar.calendarDto.CalendarDto;
import com.record.calendar.calendarService.CalendarService;

@Service
public class CalendarServiceImp implements CalendarService{

	@Autowired
	CalendarDao calendarDao;
	
	@Override
	public void insertTodayWork(CalendarDto calendarDto) {
		calendarDao.insertTodayWork(calendarDto);
	}

	@Override
	public String getAllPlan(String start) {
		Map<String, String> getDay = firstLastDay(start);
		Gson gson = new Gson(); 
		String json = gson.toJson(calendarDao.getAllPlanList(getDay));
		return json;
		
	}
	
	public Map<String, String> firstLastDay(String day) {
		LocalDate date = LocalDate.parse(day);
		LocalDate firstDay = date.withDayOfMonth(1);
		LocalDate lastDay = date.withDayOfMonth(firstDay.lengthOfMonth());
		Map<String, String> getDay = new HashMap<String, String>();
		getDay.put("firstDay", firstDay.toString());
		getDay.put("lastDay", lastDay.toString());
		return getDay;
	}

	@Override
	public CalendarDto getSelectPlan(String start) {
		return calendarDao.getSelectPlan(start);
	}
}
