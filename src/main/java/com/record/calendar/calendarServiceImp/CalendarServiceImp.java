package com.record.calendar.calendarServiceImp;

import com.record.calendar.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.record.calendar.calendarDao.CalendarDao;
import com.record.calendar.calendarDto.CalendarDto;
import com.record.calendar.calendarService.CalendarService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarServiceImp implements CalendarService{

	@Autowired
	CalendarDao calendarDao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public int insertTodayWork(CalendarDto calendarDto) {
		try {
			return calendarDao.insertTodayWork(calendarDto);
		} catch (Exception e) {
			return calendarDao.updateTodayWork(calendarDto);
		}
	}

	@Override
	public String getAllPlan(String loginMember) {
		Gson gson = new Gson();
		return gson.toJson(calendarDao.getAllPlanList(loginMember));
	}

	@Override
	public CalendarDto getSelectPlan(String start, String loginMember) {
		return calendarDao.getSelectPlan(start, loginMember);
	}

	@Override
	public int deletePlan(String start, String loginMember) {
		return calendarDao.deletePlan(start, loginMember);
	}

    @Override
    public List<CalendarDto> getPagePlan(Criteria criteria, String loginMember) {

        return calendarDao.getPlan(criteria, loginMember);
    }

}
