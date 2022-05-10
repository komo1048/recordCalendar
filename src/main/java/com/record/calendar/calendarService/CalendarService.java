package com.record.calendar.calendarService;

import com.record.calendar.calendarDto.CalendarDto;

public interface CalendarService {
	public int insertTodayWork(CalendarDto calendarDto);

	public String getAllPlan(String loginMember);

	public CalendarDto getSelectPlan(String start, String loginMember);

	public int deletePlan(String start,String loginMember);
}
