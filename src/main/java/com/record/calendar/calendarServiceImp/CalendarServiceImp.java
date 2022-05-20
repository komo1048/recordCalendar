package com.record.calendar.calendarServiceImp;

import com.record.calendar.paging.Criteria;
import com.record.calendar.paging.PagingServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.record.calendar.calendarDao.CalendarDao;
import com.record.calendar.calendarDto.CalendarDto;
import com.record.calendar.calendarService.CalendarService;

import java.util.*;

@Service
public class CalendarServiceImp implements CalendarService{

	@Autowired
	CalendarDao calendarDao;

    Criteria criteria = new Criteria();

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
        Map<String, Object> map = new HashMap<>();
        map.put("criteria", criteria);
        map.put("loginMember", loginMember);

        return calendarDao.getPlan(map);
    }

    @Override
    public String getSelectPagePlan(int page, String loginMember) {
        criteria.setCurrentPageNo(page);
        Map<String, Object> map = new HashMap<>();
        map.put("criteria", criteria);
        map.put("loginMember", loginMember);
        return new PagingServiceImp().viewPageContent(calendarDao.getPlan(map));
    }

    @Override
    public int getPageNumber(Criteria criteria, String loginMember) {
        Map<String, Object> map = new HashMap<>();
        map.put("criteria", criteria);
        map.put("loginMember", loginMember);
        int calTotalCnt = (int)Math.ceil((double) calendarDao.planTotalCnt(map) / (double) criteria.getRecordsPerPage());
        return calTotalCnt;
    }

    @Override
    public Map<String, Object> getSearchPlan(String search, String searchOption, String loginMember) {
        Map<String, Object> map = new HashMap<>();
        criteria.setSearchKeyword(search);
        criteria.setSearchType(searchOption);
        criteria.setCurrentPageNo(1);
        map.put("criteria", criteria);
        map.put("loginMember", loginMember);

        Map<String, Object> plan = new HashMap<>();
        plan.put("searchPlan", new PagingServiceImp().viewPageContent(calendarDao.getSearchPlan(map)));
        plan.put("searchPaging",  new PagingServiceImp().viewPageNumber((int)Math.ceil((double) calendarDao.searchTotalCnt(map) / (double) criteria.getRecordsPerPage())));

        return plan;
    }

    @Override
    public String getSearchSelectPage(int page, String search, String loginMember) {
        criteria.setCurrentPageNo(page);
        criteria.setSearchKeyword(search);
        Map<String, Object> map = new HashMap<>();
        map.put("criteria", criteria);
        map.put("loginMember", loginMember);

        return new PagingServiceImp().viewPageContent(calendarDao.getSearchPlan(map));
    }
}
