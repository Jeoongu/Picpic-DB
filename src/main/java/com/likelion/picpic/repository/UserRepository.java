package com.likelion.picpic.repository;

import com.likelion.picpic.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password); //로그인용
    Optional<User> findByEmail(String email);

    Optional<User> findByUuid(String uuid);
}
