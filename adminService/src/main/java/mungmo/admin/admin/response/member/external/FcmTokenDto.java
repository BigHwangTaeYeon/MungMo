package mungmo.admin.admin.response.member.external;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FcmTokenDto {
    private String fcmToken;

    public FcmTokenDto() {
    }

    public FcmTokenDto(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
