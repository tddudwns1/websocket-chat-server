package study.websocketchatserver.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import study.websocketchatserver.model.ChatMessageDTO;

@RequiredArgsConstructor
@Controller
//@RestController
//@RequestMapping("/chat")
public class ChatController {
    private final SimpMessageSendingOperations messagingTemplate;
    @MessageMapping("/chat/message")
    public void message(ChatMessageDTO message) {
        if (ChatMessageDTO.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
