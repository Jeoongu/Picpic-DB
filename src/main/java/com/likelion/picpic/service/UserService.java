package com.likelion.picpic.service;

import com.likelion.picpic.DataNotFoundException;
import com.likelion.picpic.domain.User;
import com.likelion.picpic.dto.UserJoinDto;
import com.likelion.picpic.repository.UserRepository;
import com.likelion.picpic.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    @Value("&{jwt.secret}")
    private String secretKey;
    private Long expiredMs=1000*60*60l;  //1시간

    public void join(UserJoinDto userJoinDto){
        String userEmail=userJoinDto.getEmail();
        Optional<User> optUser=userRepository.findByEmail(userEmail);
        if(optUser.isPresent()) throw new DataNotFoundException("아이디가 겹칩니다.");
        else{
            User user=User.from(userJoinDto);
            userRepository.save(user);
        }
    }
    public String login(String email, String password){
        Optional<User> optUser=userRepository.findByEmailAndPassword(email, password);
        if(optUser.isPresent()) {
            return JwtUtil.createJwt(email, secretKey, expiredMs);
        }
        else throw new DataNotFoundException("일치하는 회원 정보가 없습니다.");
    }

    public Long findUserId(String email){
        Optional<User> optUser=userRepository.findByEmail(email);
        if(optUser.isPresent()){
            return optUser.get().getId();
        }
        else throw new DataNotFoundException("이메일과 일치하는 회원 정보가 없습니다.");
    }

    public void checkEmail(String email){
        Optional<User> optUser=userRepository.findByEmail(email);
        if(optUser.isEmpty()) throw new DataNotFoundException("이메일이 이미 존재합니다.");
    }
}
