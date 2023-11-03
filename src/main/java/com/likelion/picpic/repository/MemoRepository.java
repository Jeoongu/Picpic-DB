package com.likelion.picpic.repository;

import com.likelion.picpic.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    Optional<Memo> findByXAndYAndPageNum(int x, int y, int pNum);
}
