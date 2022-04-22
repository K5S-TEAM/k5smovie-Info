package k5s.hotmovie.controller;

import k5s.hotmovie.domain.ChatRoom;
import k5s.hotmovie.domain.HotMovie;
import k5s.hotmovie.error.InvalidAuthenticationException;
import k5s.hotmovie.repository.ChatRoomRepository;
import k5s.hotmovie.service.ChatService;
import k5s.hotmovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomRepository chatRoomRepository;
    private final MovieService movieService;
    private final ChatService chatService;

//    @Value("${msa.auth}")
//    String authServerUrl;

    // 모든 채팅방 목록 반환
    @GetMapping("/chat/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomRepository.findAllRoom();
    }

    // 채팅방 입장 화면
    @GetMapping("/movies/{roomId}/chat")
    public String roomDetail(Model model, @PathVariable Long roomId
            , @CookieValue(value = "accessToken", required = false) String accessToken) {

        if (accessToken == null) {
            throw new InvalidAuthenticationException("인증 정보가 존재하지 않습니다.");
        }

        Long memberId = chatService.requestAuthentication(accessToken);
        String memberNickname = chatService.getMemberNickname(memberId);

        chatRoomRepository.createChatRoom(roomId);
        List<HotMovie> result = movieService.findOne(roomId);
        model.addAttribute("movies", result);
        model.addAttribute("memberNickname", memberNickname);
        return "chat/roomdetail";
    }
    // 특정 채팅방 조회
    @GetMapping("/chat/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }

    @ExceptionHandler
    public String invalidAuthenticationExceptionHandler(InvalidAuthenticationException e) {
        return "redirect:" + "http://3.34.45.251" + "/auth/login";
    }
}
