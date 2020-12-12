package com.yma16.springsecurity.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);//继承重写

//                auth.inMemoryAuthentication().withUser("yma16").password("123456").authorities("ROLE_USER").
//                and().withUser("sxy").password("12345").authorities("ROLE_USER");
    //jdbc添加用户
//        auth.jdbcAuthentication().dataSource(dataSource);//
//        auth.jdbcAuthentication().dataSource(dataSource).
//                usersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY).
//                authoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY);//普通查询

//        auth.jdbcAuthentication().dataSource(dataSource).
//                usersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY).
//                authoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY).
//                passwordEncoder(new StandardPasswordEncoder("53cr3t"));//密码转换器
//        //ladp作为后端存储
//        //根开始查询
//        auth.ldapAuthentication().userSearchFilter("(uid={})").groupSearchFilter("member={0}");
//        //指定基础查询
//        auth.ldapAuthentication().userSearchBase("ou=people").userSearchFilter("(uid={0})");
        auth.userDetailsService(userDetailsService);//数据库
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/admin").hasRole("admin")
                .antMatchers("/user").hasAnyRole("ADMIN","USER")
                .antMatchers("/").permitAll()
                .and().formLogin();//视图
    }
    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();//
    }

}
