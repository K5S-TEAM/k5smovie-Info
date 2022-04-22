package k5s.hotmovie.dto;

import lombok.Getter;

@Getter
public class AuthenticationRequestDto {
    String accessToken;

    public AuthenticationRequestDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
