package com.doongji.nestalk.controller.v1.friend;

import com.doongji.nestalk.controller.v1.friend.dto.FriendDto;
import com.doongji.nestalk.controller.v1.friend.dto.FriendJoinRequest;
import com.doongji.nestalk.entity.user.Friend;
import com.doongji.nestalk.security.JwtAuthentication;
import com.doongji.nestalk.service.user.FriendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "친구 APIs")
@RequestMapping("api/v1")
@RequiredArgsConstructor
@RestController
public class FriendController {

    private final FriendService friendService;

    @ApiOperation(value = "친구 등록 (JWT 불필요)")
    @PostMapping(path = "friend")
    public ResponseEntity<FriendDto> friendRegister(
            @AuthenticationPrincipal JwtAuthentication authentication,
            @RequestBody FriendJoinRequest FriendjoinRequest
    ) {
        Friend friend = friendService.join(
                authentication.userId,
                FriendjoinRequest.getFriendEmail()
        );
        return ResponseEntity.ok(new FriendDto(friend));
    }

}
