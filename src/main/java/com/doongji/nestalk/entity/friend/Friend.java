package com.doongji.nestalk.entity.friend;

import com.doongji.nestalk.entity.BaseTimeEntity;
import com.doongji.nestalk.entity.user.User;
import lombok.*;

import javax.persistence.*;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Getter
@ToString(exclude = {"me", "friend"})
@EqualsAndHashCode(of = "friendId", callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Friend extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRIEND_ID")
    private Long friendId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "me")
    private User me;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend")
    private User friend;

    private String friendNickName;

    public Friend(User me, User friend, String friendNickName){
        this(null, me, friend, friendNickName);
    }

    public Friend(Long friendId, User me, User friend, String friendNickName) {
        this.friendId = friendId;
        this.me = me;
        this.friend = friend;
        this.friendNickName = defaultIfNull(friendNickName, friend.getName());
    }

}
