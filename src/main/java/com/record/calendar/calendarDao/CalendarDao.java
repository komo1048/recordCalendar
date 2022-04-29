package com.record.calendar.calendarDao;

import org.apache.ibatis.annotations.Mapper;

import com.record.calendar.calendarDto.CalendarDto;

@Mapper
public interface CalendarDao {

	public void insertTodayWork(CalendarDto calendarDto);
	
}
