package com.record.calendar.memberService;

import com.record.calendar.memberDto.MemberDto;

public interface MemberService {
    int register(MemberDto memberDto);

    int checkId(MemberDto memberDto);

    int login(MemberDto memberDto);

    int updateTempPassword(MemberDto memberDto);

    MemberDto getMember(String loginMember);

    int updateUser(MemberDto memberDto, String pwdFlag);
}
