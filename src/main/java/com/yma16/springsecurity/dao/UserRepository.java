package com.yma16.springsecurity.dao;

import com.yma16.springsecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String userName);//接口方法，用户名查找
}
