package study.websocketchatserver.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import study.websocketchatserver.model.ChatRoomDTO;

import java.util.*;

@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoomDTO> chatRoomMap;
    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }
    public List<ChatRoomDTO> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }
    public ChatRoomDTO findRoomById(String id) {
        return chatRoomMap.get(id);
    }
    public ChatRoomDTO createChatRoom(String name) {
        ChatRoomDTO chatRoom = ChatRoomDTO.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}