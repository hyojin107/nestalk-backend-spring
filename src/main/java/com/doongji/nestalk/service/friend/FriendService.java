package com.doongji.nestalk.service.friend;

import com.doongji.nestalk.entity.friend.Friend;
import com.doongji.nestalk.entity.user.User;
import com.doongji.nestalk.error.NotFoundException;
import com.doongji.nestalk.repository.friend.FriendRepository;
import com.doongji.nestalk.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                        friendRepository.save(
                                new Friend(
                                        findById(userId), friend
                                )
                        )
                )
                .orElseThrow(() -> new NotFoundException(User.class, friendEmail));
    }

    @Transactional
    public Friend update(Long userId, Long friendId, String nickname) {
        return friendRepository.findByMeAndFriend(
                findById(userId),
                userRepository.findById(friendId)
                        .orElseThrow(() -> new NotFoundException(User.class, friendId))
        ).map(friend -> {
            friend.updateNickName(nickname);
            return friend;
        })
                .orElseThrow(() ->
                        new NotFoundException(Friend.class, friendId)
                );
    }

    @Transactional(readOnly = true)
    protected User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(User.class, userId));
    }

    @Transactional
    public List<Friend> friendList(Long userId) {
        return friendRepository.findAllByMe(
                userRepository.findById(userId)
                        .orElseThrow(() -> new NotFoundException(User.class, userId))
        );
    }

}
