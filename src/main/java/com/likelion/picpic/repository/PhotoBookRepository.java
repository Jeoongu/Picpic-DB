package com.likelion.picpic.repository;

import com.likelion.picpic.domain.PhotoBook;
import com.likelion.picpic.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoBookRepository extends JpaRepository<PhotoBook, Long> {
    Optional<PhotoBook> findByUser(User user);
}
