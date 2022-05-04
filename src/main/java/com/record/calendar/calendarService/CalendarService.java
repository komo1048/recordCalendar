package com.record.calendar.calendarService;

import com.record.calendar.calendarDto.CalendarDto;

public interface CalendarService {
	public int insertTodayWork(CalendarDto calendarDto);

	public String getAllPlan(String start);

	public CalendarDto getSelectPlan(String start);

	public int deletePlan(String start);
}
