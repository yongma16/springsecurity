package com.yma16.springsecurity.service;

import com.yma16.springsecurity.dao.UserRepository;
import com.yma16.springsecurity.models.MyUserDetails;
import com.yma16.springsecurity.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import  java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        Optional<User> user=userRepository.findByUserName(userName);//接口实现
//        user.map(MyUserDetails::new).get();
        user.orElseThrow(() -> new UsernameNotFoundException("没有找到该用户"+userName));
        return user.map(MyUserDetails::new).get();//返回用户
    }
}
