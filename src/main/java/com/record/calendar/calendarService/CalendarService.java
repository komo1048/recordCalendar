package com.record.calendar.calendarService;

import com.record.calendar.calendarDto.CalendarDto;
import com.record.calendar.paging.Criteria;

import java.util.List;
import java.util.Map;

public interface CalendarService {
	public int insertTodayWork(CalendarDto calendarDto);

	public String getAllPlan(String loginMember);

	public CalendarDto getSelectPlan(String start, String loginMember);

	public int deletePlan(String start,String loginMember);

    List<CalendarDto> getPagePlan(Criteria criteria,String loginMember);

    String getSelectPagePlan(int page, String loginMember);

    int getPageNumber(Criteria criteria, String loginMember);

    Map<String, Object> getSearchPlan(String search,String searchOption, String loginMember);

    String getSearchSelectPage(int page, String title, String loginMember);
}
