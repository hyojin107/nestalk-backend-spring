package com.doongji.nestalk.service.friend;

import com.doongji.nestalk.entity.user.Friend;
import com.doongji.nestalk.entity.user.User;
import com.doongji.nestalk.service.user.FriendService;
import com.doongji.nestalk.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FriendServiceTest {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    private String friendEmail;

    private String email;

    private String name;

    private String password;

    private String phone;

    private Long userId = 1L;

    @Test
    @Order(1)
    void 사용자_회원가입() {
        userService.join("doongji.team@gmail.com", "둥지", "P@ssword1", "010-0000-0000", LocalDate.of(1995, 10, 7));
        userService.join("newdoongji.team@naver.com", "친구친구", "P@ssword1", "010-1111-0000", LocalDate.of(1995, 2, 19));
    }

    @Test
    @Order(2)
    void 친구_등록() {
        Friend friend = friendService.register(1L, "newdoongji.team@naver.com");
        assertThat(friend).isNotNull();
        assertThat(friend.getMe().getUserId()).isEqualTo(1L);
        assertThat(friend.getFriend().getEmail()).isEqualTo("newdoongji.team@naver.com");
        log.info("Friend: {}", friend);
    }

}
