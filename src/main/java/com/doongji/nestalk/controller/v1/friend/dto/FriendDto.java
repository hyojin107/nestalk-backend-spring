package com.doongji.nestalk.controller.v1.friend.dto;

import com.doongji.nestalk.controller.v1.user.dto.UserDto;
import com.doongji.nestalk.entity.friend.Friend;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FriendDto {

    @ApiModelProperty(value = "PK", required = true)
    private Long friendId;

    @ApiModelProperty(value = "사용자 객체", required = true)
    private UserDto me;

    @ApiModelProperty(value = "친구 객체", required = true)
    private UserDto friend;

    @ApiModelProperty(value = "친구 닉네임", required = true)
    private String friendNickName;

    public FriendDto(Friend source) {
        copyProperties(source, this);
    }

}
