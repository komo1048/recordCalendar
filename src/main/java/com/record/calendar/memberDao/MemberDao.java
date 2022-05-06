package com.record.calendar.memberDao;

import com.record.calendar.memberDto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    int registerMember(MemberDto memberDto);

    int checkId(MemberDto memberDto);
}