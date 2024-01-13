package study.websocketchatserver.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import study.websocketchatserver.model.ChatMessageDTO;
import study.websocketchatserver.model.ChatRoomDTO;
import study.websocketchatserver.service.ChatService;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSockChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
        //TextMessage textMessage = new TextMessage("Welcome chatting sever~^^ ");
        //session.sendMessage(textMessage);
        ChatMessageDTO chatMessage = objectMapper.readValue(payload, ChatMessageDTO.class);
        ChatRoomDTO room = chatService.findRoomById(chatMessage.getRoomId());
        room.handleActions(session, chatMessage, chatService);
    }
}