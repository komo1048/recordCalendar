package com.record.calendar.memberServiceImp;

import com.record.calendar.memberDao.MemberDao;
import com.record.calendar.memberDto.MemberDto;
import com.record.calendar.memberService.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class MemberServiceImp implements MemberService {


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
            log.error("이미 존재하는 아이디 입니다.");
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
                log.error("비밀번호가 일치하지 않습니다.");
                return 0;
            }
        } catch (Exception e) {
            log.error("없는 회원이거나 비밀번호가 일치하지 않습니다.");
            return 0;
        }
    }

    @Override
    public int updateTempPassword(MemberDto memberDto) {
        memberDto.setPassword(encodePassword(memberDto.getPassword()));
        return memberDao.updateTempPwd(memberDto);
    }

    @Override
    public MemberDto getMember(String loginMember) {
        MemberDto memberDto = memberDao.findMember(loginMember);
        memberDto.setRegdate(dateFormatModify(memberDto.getRegdate()));
        return memberDto;
    }

    @Transactional
    public String encodePassword(String pass){
        return passwordEncoder.encode(pass);
    }

    public boolean checkPass(MemberDto memberDto){
        MemberDto member = memberDao.findMember(memberDto.getId());
        if(passwordEncoder.matches(memberDto.getPassword(), member.getPassword())){
            return true;
        }else{
            return false;
        }
    }

    public String dateFormatModify(String format){
        return format.split(" ")[0];
    }

    @Override
    public int updateUser(MemberDto memberDto, String pwdFlag) {
        Map<String, Object> map = new HashMap<>();
        map.put("pwdFlag", pwdFlag);
        if(memberDto.getPassword() != null && memberDto.getPassword() != "") {
            memberDto.setPassword(encodePassword(memberDto.getPassword()));
        }
        map.put("memberDto", memberDto);
        return memberDao.updateMember(map);
    }
}
