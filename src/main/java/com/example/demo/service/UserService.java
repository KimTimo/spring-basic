package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    // Login
    @Override
    public UserDetails loadUserByUsername(String memId) throws UsernameNotFoundException {
        User user = userMapper.findTargetUserInfo(memId);

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException(memId);
        }
    }

    // Join
    public void joinUser(UserDto userDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String memPassword = encoder.encode(userDto.getMemPassword());
        userDto.setMemPassword(memPassword);


//        userDto.setMemPassword(passwordEncoder.encode(userDto.getMemPassword()));
        userMapper.joinUser(userDto);
    }

    // 내 정보
    public UserDto userInfo(int memNo){
        return userMapper.userInfo(memNo);
    }

    // 회원정보 수정
    public void updateUser(UserDto userDto){
        userMapper.updateUser(userDto);
    }

    // ID 중복체크
    public int idCheck(String memId){
        return userMapper.idCheck(memId);
    }
}
