package com.a306.fanftasy.domain.user.repository;

import com.a306.fanftasy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
