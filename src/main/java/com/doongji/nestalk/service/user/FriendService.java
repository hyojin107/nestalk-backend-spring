package com.doongji.nestalk.service.user;

import com.doongji.nestalk.entity.user.Friend;
import com.doongji.nestalk.entity.user.User;
import com.doongji.nestalk.error.NotFoundException;
import com.doongji.nestalk.repository.friend.FriendRepository;
import com.doongji.nestalk.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.google.common.base.Preconditions.checkNotNull;

@RequiredArgsConstructor
@Service
public class FriendService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    @Transactional
    public Friend register(Long userId, String friendEmail){
        checkNotNull(friendEmail, "friendEmail must be provided.");

        //친구의 user객체 가져오기
        User friend = userRepository.findByEmail(friendEmail)
                        .orElseThrow(() -> new NotFoundException(User.class, friendEmail));
        return friendRepository.save(new Friend(userId, friend));
    }

}
