package com.doongji.nestalk.repository.friend;

import com.doongji.nestalk.entity.user.Friend;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FriendRepository extends JpaRepository<Friend, Long> {
}
