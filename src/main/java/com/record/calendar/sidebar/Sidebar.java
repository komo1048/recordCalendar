package com.record.calendar.sidebar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Sidebar {
    @RequestMapping("/roulette")
    public String roulette(){
        return "roulette";
    }
}
