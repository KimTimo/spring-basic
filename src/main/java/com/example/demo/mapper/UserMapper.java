package com.example.demo.mapper;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    // Login
    User findTargetUserInfo(String memId);

    // Join
    void joinUser(UserDto userDto);

    // 내 정보
    UserDto userInfo(@Param("memNo") int memNo);

    // 회원정보 수정
    void updateUser(UserDto userDto);

    // ID 중복체크
    int idCheck(@Param("memId") String memId);
}
