package k5s.hotmovie.service;

import k5s.hotmovie.dto.AuthenticationRequestDto;
import k5s.hotmovie.dto.AuthenticationResponseDto;
import k5s.hotmovie.dto.MemberNicknameResponseDto;
import k5s.hotmovie.error.InvalidAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ChatService {

    private final String authServerUrl = "http://13.209.76.94:8080";
    private final String memberConvenienceServerUrl = "http://54.180.106.16:8081";

    @Transactional
    public Long requestAuthentication(String accessToken) {
        AuthenticationRequestDto dto = new AuthenticationRequestDto(accessToken);

        log.info("reqeustAuthentication: " + accessToken);

        WebClient webClient = WebClient.builder().baseUrl(authServerUrl).build();
        AuthenticationResponseDto result = webClient.post()
                .uri("/auth/access-token-valid")
                .body(Mono.just(dto), AuthenticationRequestDto.class)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new InvalidAuthenticationException("인증 정보가 존재하지 않습니다.")))
                .bodyToMono(AuthenticationResponseDto.class)
                .block();

        if (result.getId() == null) {
            throw new InvalidAuthenticationException("인증 정보가 존재하지 않습니다.");
        }

        return result.getId();
    }

    @Transactional
    public String getMemberNickname(Long memberId) {
        WebClient webClient = WebClient.builder().baseUrl(memberConvenienceServerUrl).build();
        MemberNicknameResponseDto result = webClient.get()
                .uri("/member/" + memberId + "/nickname")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, error -> Mono.error(new InvalidAuthenticationException("사용자 정보가 존재하지 않습니다.")))
                .bodyToMono(MemberNicknameResponseDto.class)
                .block();

        return result.getMemberNickname();
    }
}
