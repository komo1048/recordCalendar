package com.record.calendar.memberServiceImp;

import com.record.calendar.memberDao.MemberDao;
import com.record.calendar.memberDto.MemberDto;
import com.record.calendar.memberService.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImp implements MemberService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberDao memberDao;

    @Override
    public int register(MemberDto memberDto) {
        try {
            return memberDao.registerMember(memberDto);
        }catch (Exception e){
            logger.error("이미 존재하는 아이디 입니다.");
            return 0;
        }
    }

    @Override
    public int checkId(MemberDto memberDto) {
        return memberDao.checkId(memberDto);
    }

}
