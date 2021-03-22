package com.doongji.nestalk.service.friend;

import com.doongji.nestalk.entity.friend.Friend;
import com.doongji.nestalk.entity.user.User;
import com.doongji.nestalk.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FriendServiceTest {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 친구_등록() {
        //given
        userRepository.save(new User("doongji.team@gmail.com", "둥지", "P@ssword1", "010-0000-0000", LocalDate.of(1995, 10, 7)));
        userRepository.save(new User("newdoongji.team@naver.com", "친구친구", "P@ssword1", "010-1111-0000", LocalDate.of(1995, 2, 19)));

        //when
        Friend friend = friendService.register(1L, "newdoongji.team@naver.com");

        //then
        assertThat(friend).isNotNull();
        assertThat(friend.getMe().getUserId()).isEqualTo(1L);
        assertThat(friend.getFriend().getEmail()).isEqualTo("newdoongji.team@naver.com");
        assertThat(friend.getFriend().getName()).isEqualTo("친구친구");
        assertThat(friend.getFriend().getPhone()).isEqualTo("010-1111-0000");
        assertThat(friend.getFriend().getBirthday()).isEqualTo(LocalDate.of(1995,2,19));
        log.info("Friend: {}", friend);
    }

}
