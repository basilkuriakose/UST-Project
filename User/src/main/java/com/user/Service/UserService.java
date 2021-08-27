package com.user.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.user.Entity.User;


@Service
public interface UserService extends JpaRepository<User, Long> {

}
