package k5s.hotmovie.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {

    private Long roomId;
//    private String roomName;

    public static ChatRoom create(Long id) {
        ChatRoom room = new ChatRoom();
        room.roomId = id;
//        room.roomName = name;
        return room;
    }
}
