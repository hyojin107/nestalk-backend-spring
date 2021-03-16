package com.doongji.nestalk.repository.friend;

import com.doongji.nestalk.entity.user.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FriendRepository extends JpaRepository<Friend, Long> {

}
