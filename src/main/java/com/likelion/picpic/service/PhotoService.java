package com.likelion.picpic.service;

import com.likelion.picpic.DataNotFoundException;
import com.likelion.picpic.domain.Photo;
import com.likelion.picpic.domain.User;
import com.likelion.picpic.repository.PhotoRepository;
import com.likelion.picpic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    public void savePhoto(Long userId, String url){
        Optional<User> optUser=userRepository.findById(userId);
        if(optUser.isEmpty()) throw new DataNotFoundException("유저를 찾지 못하였습니다.");
        User user=optUser.get();
        Photo photo=new Photo();
        photo.setUrl(url);
        photo.setUser(user);
        photoRepository.save(photo);
        return;
    }
    // 이렇게만 해도 되나?
    public void deletePhoto(Long userId, String url){
//        Optional<User> optUser=userRepository.findById(userId);
//        if(optUser.isEmpty()) throw new DataNotFoundException("유저를 찾지 못하였습니다.");
//        User user=optUser.get();
        Optional<Photo> optPhoto = photoRepository.findByUrl(url);
        if(optPhoto.isEmpty()) throw new DataNotFoundException("포토를 찾지 못하였습니다.");
        Photo photo = optPhoto.get();

        photoRepository.delete(photo);
    }
}

