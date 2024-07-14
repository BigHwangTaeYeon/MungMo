package mungmo.mungmoChat.domain.room.service;

import mungmo.mungmoChat.domain.room.domain.ChatRoom;
import mungmo.mungmoChat.domain.massage.repository.ChatRoomRepository;
import mungmo.mungmoChat.domain.room.dto.ChatRoomDTO;
import mungmo.mungmoChat.otherDomain.member.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final MemberService memberService;

    public ChatRoomService(ChatRoomRepository chatRoomRepository, MemberService memberService) {
        this.chatRoomRepository = chatRoomRepository;
        this.memberService = memberService;
    }

    public void createMeetup(ChatRoomDTO chatRoom) {
        ChatRoom room = ChatRoom.of(chatRoom, memberService.findMemberById(chatRoom.getOwner().getMemberId()));
        chatRoomRepository.save(room);
    }
}
