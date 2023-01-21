package com.example.demo.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("userDto")
public class UserDto {

    private int memNo;
    private String memRole;
    private String memUsername;
    private String memId;
    private String memPassword;

}
