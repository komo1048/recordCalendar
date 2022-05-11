package com.record.calendar.memberServiceImp;

import com.record.calendar.memberDao.MemberDao;
import com.record.calendar.memberDto.MemberDto;
import com.record.calendar.memberService.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImp implements MemberService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberDao memberDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public int register(MemberDto memberDto) {
        try {
            memberDto.setPassword(encodePassword(memberDto.getPassword()));
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

    @Override
    public int login(MemberDto memberDto) {
        try {
            if(checkPass(memberDto) && memberDao.login(memberDto)){
                return 1;
            }else {
                logger.error("비밀번호가 일치하지 않습니다.");
                return 0;
            }
        } catch (Exception e) {
            logger.error("없는 회원이거나 비밀번호가 일치하지 않습니다.");
            return 0;
        }
    }

    @Override
    public int updateTempPassword(MemberDto memberDto) {
        memberDto.setPassword(encodePassword(memberDto.getPassword()));
        return memberDao.updateTempPwd(memberDto);
    }

    @Transactional
    public String encodePassword(String pass){
        return passwordEncoder.encode(pass);
    }

    public boolean checkPass(MemberDto memberDto){
        MemberDto member = memberDao.findMember(memberDto);
        if(passwordEncoder.matches(memberDto.getPassword(), member.getPassword())){
            return true;
        }else{
            return false;
        }
    }



}
