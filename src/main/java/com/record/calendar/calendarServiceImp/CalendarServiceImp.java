package com.record.calendar.calendarServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
