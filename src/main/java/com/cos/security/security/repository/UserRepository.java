package com.cos.security.security.repository;

import com.cos.security.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//기본적인 crud 함수를 들고 있음
// @Repository라는 어노테이션이 없어도 IOC 됨, 이유는 JpaRepository 를 상송했기 때문
public interface UserRepository extends JpaRepository<User, Integer> {
    //findBy 규칙 -> Username 문법

    public User findByUsername(String username);
}
