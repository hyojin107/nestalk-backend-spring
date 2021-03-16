package com.doongji.nestalk.entity.user;

import com.doongji.nestalk.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@EqualsAndHashCode(of = "friendId", callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Friend extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRIEND_ID")
    private Long friendId;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User me;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TARGET_ID")
    private User friend;

    private String friendNickName;

    public Friend(Long userId, User friend) {
        this.me = new User(userId);
        this.friend = friend;
        this.friendNickName = friend.getName();
    }

}
