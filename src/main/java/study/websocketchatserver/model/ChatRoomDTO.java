package study.websocketchatserver.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;
import study.websocketchatserver.service.ChatService;

import java.util.HashSet;
import java.util.Set;

import static study.websocketchatserver.model.ChatMessageDTO.*;

@Getter
public class ChatRoomDTO {
    private String roomId;
    private String name;
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoomDTO(String roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }

    public void handleActions(WebSocketSession session, ChatMessageDTO ChatMessageDTO, ChatService chatService) {
        if (ChatMessageDTO.getType().equals(MessageType.ENTER)) {
            sessions.add(session);
            ChatMessageDTO.setMessage(ChatMessageDTO.getSender() + "님이 입장했습니다.");
        }
        sendMessage(ChatMessageDTO, chatService);
    }

    public <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }
}
