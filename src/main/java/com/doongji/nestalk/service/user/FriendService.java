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
    public Friend register(Long userId, String friendEmail) {
        checkNotNull(friendEmail, "friendEmail must be provided.");

        return userRepository.findByEmail(friendEmail)
                .map(friend ->
                    friendRepository.save(new Friend(
                            userRepository.findById(userId).orElseThrow(() -> new NotFoundException(User.class, userId)),
                            friend,
                            null)
                    )
                )
                .orElseThrow(() -> new NotFoundException(User.class, friendEmail));
    }

}
