package com.record.calendar.calendarDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.record.calendar.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import com.record.calendar.calendarDto.CalendarDto;

@Mapper
public interface CalendarDao {

	public int insertTodayWork(CalendarDto calendarDto);

	public ArrayList<CalendarDto> getAllPlanList(String loginMember);

	public CalendarDto getSelectPlan(String start, String loginMember);

	public int deletePlan(String start, String loginMember);
	
	public int updateTodayWork(CalendarDto calendarDto);

    List<CalendarDto> getPlan(Map<String, Object> map);

    public int planTotalCnt(Map<String, Object> map);

    List<CalendarDto> getSearchPlan(Map<String, Object> map);

    int searchTotalCnt(Map<String, Object> map);
}
