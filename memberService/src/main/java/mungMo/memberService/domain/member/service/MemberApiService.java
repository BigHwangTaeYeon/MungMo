package mungMo.memberService.domain.member.service;

import mungMo.memberService.com.config.ResponseMessage;
import mungMo.memberService.com.exception.FileUploadException;
import mungMo.memberService.com.exception.ValidationException;
import mungMo.memberService.com.util.Upload;
import mungMo.memberService.com.util.Validation;
import mungMo.memberService.domain.embede.FileInfo;
import mungMo.memberService.domain.member.repository.MemberRepository;
import mungMo.memberService.domain.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class MemberApiService {

    private final MemberRepository memberRepository;

    @Value("${api.upload.dir.review}")
    private String uploadDir;

    public MemberApiService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public boolean checkIfEnabledNickName(String nickname) throws ValidationException {
        Validation.nickname(nickname);
        return Optional.ofNullable(memberRepository.findByNickname(nickname))
                .map(MemberEntity::getNickname)
                .isPresent();
    }

    @Transactional
    public void registerNickname(String nickName, Long id) throws ValidationException {

        if(!checkIfEnabledNickName(nickName)){
            memberRepository.findById(id)
                    .ifPresent(entity -> {
                        entity.changeNickname(nickName);
                    });
        } else {
            throw new ValidationException(ResponseMessage.valueOfCode("Conflict").getMessage());
        }
    }

    @Transactional
    public void updateDogImg(long id, MultipartFile file) {
        Optional.of(file).ifPresent(img -> {
                memberRepository.findById(id).ifPresent(entity -> {
                    try {
                        entity.fileInfoInstance(new FileInfo(new Upload(uploadDir, img).uploadImage(), "img"));
                    } catch (FileUploadException e) {
                        throw new RuntimeException(e);
                    }
                });
        });
    }
}
