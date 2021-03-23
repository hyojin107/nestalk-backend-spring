package com.doongji.nestalk.entity.friend;

import com.doongji.nestalk.entity.BaseTimeEntity;
import com.doongji.nestalk.entity.user.User;
import lombok.*;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Getter
@ToString(exclude = {"me", "friend"})
@EqualsAndHashCode(of = "friendId", callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Friend extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User me;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id")
    private User friend;

    private String friendNickName;

    public Friend(User me, User friend){
        this(null, me, friend, null);
    }

    public Friend(Long friendId, User me, User friend, String friendNickName) {
        checkNotNull(me.getUserId(), "userId must be provided.");
        checkNotNull(friend.getEmail(), "friend's email must be provided.");

        this.friendId = friendId;
        this.me = me;
        this.friend = friend;
        this.friendNickName = defaultIfNull(friendNickName, friend.getName());
    }

}
