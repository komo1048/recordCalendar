package com.record.calendar.memberDao;

import com.record.calendar.memberDto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    int registerMember(MemberDto memberDto);

    int checkId(MemberDto memberDto);

    boolean login(MemberDto memberDto);

    MemberDto findMember(String loginMember);

    int updateTempPwd(MemberDto memberDto);

    MemberDto getWhoIs(String loginMember);
}
