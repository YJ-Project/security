package com.cos.security.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 활성화 시켜야해서 -> 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //해당 매소드의 리턴되는 오브젝트를 ioc로 등록해준다
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
            .antMatchers("/user/**").authenticated()
            .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/loginForm")
            .loginProcessingUrl("/login") //login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행함
            .defaultSuccessUrl("/")
            .and()
            .oauth2Login()
            .loginPage("/loginForm")
            ;
    }
}
