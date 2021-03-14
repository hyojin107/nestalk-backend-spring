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

    private LocalDate birthday;

    private User user;

    @BeforeAll
    void setup(){
        friendEmail = "newFriend@gmail.com";
        name = "둥지";
        email = "doongji.team@gmail.com";
        password = "P@ssword1";
        phone = "010-0000-0000";
        birthday = LocalDate.of(1995, 2, 19);
    }

    @Test
    @Order(1)
    void 사용자_회원가입() {
        User user = userService.join(email, name, password, phone, birthday);
        this.user = user;
        log.info("User: {}", user);
    }

    @Test
    @Order(2)
    void 친구_등록(){
        Friend friend = friendService.join(user.getUserId(), friendEmail);
        assertThat(friend).isNotNull();
        assertThat(friend.getMe().getUserId()).isEqualTo(user.getUserId());
        assertThat(friend.getFriend()).isEqualTo(friendEmail);
        log.info("Friend: {}", friend);
    }

}
