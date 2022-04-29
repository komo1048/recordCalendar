package com.record.calendar.calendarDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CalendarDto {

	private int number;
	private String title;
	private String content;
	private String insertDate;
}
