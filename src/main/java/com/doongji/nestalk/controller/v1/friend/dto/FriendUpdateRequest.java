package com.doongji.nestalk.controller.v1.friend.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendUpdateRequest {

    @ApiModelProperty(value = "친구 pk값", required = true)
    private Long friendId;

    @ApiModelProperty(value = "친구 닉네임", required = true)
    private String nickname;

}
