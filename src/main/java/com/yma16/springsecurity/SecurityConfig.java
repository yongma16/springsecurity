package com.yma16.springsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);//继承重写
        //添加用户
        auth.inMemoryAuthentication().withUser("yma16").password("123456").authorities("ROLE_USER").
                and().withUser("sxy").password("12345").authorities("ROLE_USER");
    }
}
