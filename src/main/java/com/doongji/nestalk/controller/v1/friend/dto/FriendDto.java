package com.doongji.nestalk.controller.v1.friend.dto;

import com.doongji.nestalk.entity.user.Friend;
import com.doongji.nestalk.entity.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class FriendDto {

    @ApiModelProperty(value = "PK", required = true)
    private Long friendId;

    @ApiModelProperty(value = "사용자 객체", required = true)
    private User me;

    @ApiModelProperty(value = "친구 객체", required = true)
    private User friend;

    public FriendDto(Friend source) {
        copyProperties(source, this);
    }

}
