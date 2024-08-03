package mungMo.member.domain.member.fcm.service;

import lombok.RequiredArgsConstructor;
import mungMo.member.domain.member.fcm.domain.FcmToken;
import mungMo.member.domain.member.fcm.repository.FcmRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FcmService {
    private final FcmRepository fcmRepository;

    public FcmToken findToken(Long id) {
        return fcmRepository.findById(id).orElseThrow();
    }
}
