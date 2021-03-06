package com.record.calendar.paging;

import com.record.calendar.calendarDto.CalendarDto;

import java.util.List;

public class PagingServiceImp {

    public String viewPageContent(List<CalendarDto> calendarList){
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"profile-comments\">");
        for(int i = 0; i< calendarList.size(); i++){
            sb.append("<div class=\"profile-comments__item\">\n" +
                    "<div class=\"profile-comments__controls\">\n" +
                    "    <a href=\"#\"><i class=\"fa fa-share-square-o\"></i></a>\n" +
                    "    <a href=\"#\" onclick=\"getData.modifyPlan(\'"+calendarList.get(i).getStart()+"\')\"><i class=\"fa fa-edit\"></i></a>\n" +
                    "    <a href=\"#\"><i class=\"fa fa-trash-o\"></i></a>\n" +
                    "</div>\n" +
                    "<div class=\"profile-comments__avatar\">\n" +
                    "    <i class=\"bi bi-calendar-check-fill fa-2x\"></i>\n" +
                    "</div>\n" +
                    "<div class=\"profile-comments__body\">\n" +
                    "    <h5 class=\"profile-comments__sender\">");
            sb.append(calendarList.get(i).getTitle());
            sb.append("<small>" + calendarList.get(i).getStart() + "</small>");
            sb.append("</h5>");
            sb.append("<div class=\"profile-comments__content\">");
            sb.append(calendarList.get(i).getContent());
            sb.append("</div>\n");
            sb.append("</div>\n");
            sb.append("</div>\n");
        }
        sb.append("</div>");
        return sb.toString();
    }

    public String viewPageNumber(int searchTotalCnt) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= searchTotalCnt; i ++){
            sb.append("<li class=\"page-item\"><a class=\"page-link\" href=\"#\" onclick=\"getData.searchSelectPage("+i+")\">"+i+"</a></li>");
        }
        return sb.toString();
    }
}
