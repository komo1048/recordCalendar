package com.record.calendar.calendarDao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.record.calendar.calendarDto.CalendarDto;

@Mapper
public interface CalendarDao {

	public void insertTodayWork(CalendarDto calendarDto);

	public ArrayList<CalendarDto> getAllPlanList(Map<String, String> start);

	public CalendarDto getSelectPlan(String start);
}
