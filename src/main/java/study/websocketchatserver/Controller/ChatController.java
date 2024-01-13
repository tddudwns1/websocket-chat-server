package study.websocketchatserver.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.websocketchatserver.model.ChatRoomDTO;
import study.websocketchatserver.service.ChatService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ChatRoomDTO createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoomDTO> findAllRoom() {
        return chatService.findAllRoom();
    }
}
