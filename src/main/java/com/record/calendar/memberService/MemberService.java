package com.record.calendar.memberService;

import com.record.calendar.memberDto.MemberDto;

public interface MemberService {
    int register(MemberDto memberDto);

    int checkId(MemberDto memberDto);
}