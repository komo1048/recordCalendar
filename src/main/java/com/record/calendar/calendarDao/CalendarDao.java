package com.record.calendar.calendarDao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.record.calendar.calendarDto.CalendarDto;

@Mapper
public interface CalendarDao {

	public void insertTodayWork(CalendarDto calendarDto);

	public ArrayList<CalendarDto> getAllPlanList();

	public CalendarDto getSelectPlan(String start);
}
