package com.likelion.picpic.repository;

import com.likelion.picpic.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
