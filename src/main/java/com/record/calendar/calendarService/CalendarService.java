package com.record.calendar.calendarService;

import com.record.calendar.calendarDto.CalendarDto;
import com.record.calendar.paging.Criteria;

import java.util.List;

public interface CalendarService {
	public int insertTodayWork(CalendarDto calendarDto);

	public String getAllPlan(String loginMember);

	public CalendarDto getSelectPlan(String start, String loginMember);

	public int deletePlan(String start,String loginMember);

    List<CalendarDto> getPagePlan(Criteria criteria,String loginMember);

    List<CalendarDto> getSelectPagePlan(int page, String loginMember, Criteria criteria);

    int getPageNumber(Criteria criteria, String loginMember);
}
