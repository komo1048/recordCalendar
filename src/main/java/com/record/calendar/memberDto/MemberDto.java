package com.record.calendar.memberDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberDto {

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
