package com.doongji.nestalk.repository.friend;

import com.doongji.nestalk.entity.friend.Friend;
import com.doongji.nestalk.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    Optional<Friend> findByMeAndFriend(User me, User friend);

    List<Friend> findAllByMe(User me);

}
