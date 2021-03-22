package com.doongji.nestalk.controller.v1.friend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendJoinRequest {

    @ApiModelProperty(value = "친구 이메일", required = true)
    private String friendEmail;

    public FriendJoinRequest(String friendEmail) {
        this.friendEmail = friendEmail;
    }

}
