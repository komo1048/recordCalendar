package com.record.calendar.memberDto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Setter
@Getter
@ToString
public class MemberDto{

    private int number;
    private String id;
    private String password;
    private String phone;
    private String email;
    private String name;
    private String address;
    private String job;
    private String regdate;
}
