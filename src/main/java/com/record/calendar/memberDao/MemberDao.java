package com.record.calendar.memberDao;

import com.record.calendar.memberDto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MemberDao {
    int registerMember(MemberDto memberDto);

    int checkId(MemberDto memberDto);

    boolean login(MemberDto memberDto);

    MemberDto findMember(String loginMember);

    int updateTempPwd(MemberDto memberDto);

    int updateMember(Map<String, Object> map);
}
