package com.likelion.picpic.service;


import com.likelion.picpic.DataNotFoundException;
import com.likelion.picpic.domain.Memo;
import com.likelion.picpic.domain.PhotoBook;
import com.likelion.picpic.domain.User;
import com.likelion.picpic.dto.MemoCreateDto;
import com.likelion.picpic.repository.MemoRepository;
import com.likelion.picpic.repository.PhotoBookRepository;
import com.likelion.picpic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    private final PhotoBookRepository photoBookRepository;
    private final UserRepository userRepository;

    public void saveMemo(MemoCreateDto memoCreateDto, Long userId){
        Optional<User> OptUser=userRepository.findById(userId);
        if(OptUser.isPresent()){
            User user=OptUser.get();
            Optional<PhotoBook> optP=photoBookRepository.findByUser(user);
            if(optP.isPresent()){
                PhotoBook photoBook=optP.get();
                Memo memo=Memo.from(memoCreateDto, photoBook);
                memoRepository.save(memo);
            }
            else throw new DataNotFoundException("포토북이 없습니다.");
        }
        else throw new DataNotFoundException("유저가 없습니다.");
    }

    public void deleteMemo(MemoCreateDto memoCreateDto){
        int x= memoCreateDto.getX();
        int y= memoCreateDto.getY();
        int pNum= memoCreateDto.getPageNum();
        Optional<Memo> optMemo=memoRepository.findByXAndYAndPageNum(x,y,pNum);
        if(optMemo.isPresent()){
            Memo memo=optMemo.get();
            memoRepository.delete(memo);
        }
        else throw new DataNotFoundException("메모가 없습니다");
    }


}
