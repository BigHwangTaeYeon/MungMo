package mungMo.member.domain.member.facade;

import lombok.RequiredArgsConstructor;
import mungMo.member.com.exception.FileUploadException;
import mungMo.member.com.exception.PreconditionFailedException;
import mungMo.member.domain.member.entity.MemberEntity;
import mungMo.member.domain.member.external.DogImgDto;
import mungMo.member.domain.member.external.MemberDto;
import mungMo.member.domain.member.external.MemberIdAndDogNameDto;
import mungMo.member.domain.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberService memberService;

    @Transactional(readOnly = true)
    public MemberDto infoById(Long id) throws PreconditionFailedException {
        return memberService.infoById(id).toDto();
    }

    @Transactional(readOnly = true)
    public DogImgDto dogImg(Long id) throws PreconditionFailedException {
        return memberService.infoById(id).dogImgDTO();
    }

    @Transactional(readOnly = true)
    public MemberIdAndDogNameDto dogName(Long id) throws PreconditionFailedException {
        return memberService.infoById(id).toDogDTO();
    }

    @Transactional
    public void registerNickname(String dogName, Long id) throws PreconditionFailedException {
        MemberEntity member = memberService.infoById(id);
        memberService.registerNickname(dogName, member);
    }

    @Transactional
    public void updateDogImg(Long id, MultipartFile file) throws PreconditionFailedException, FileUploadException {
        MemberEntity member = memberService.infoById(id);
        memberService.updateDogImg(member, file);
    }

    @Transactional
    public void dogLike(Long id, String like) throws PreconditionFailedException {
        MemberEntity member = memberService.infoById(id);
        memberService.dogLike(member, like);
    }

    @Transactional
    public void registerFcm(String fcmToken, Long id) throws PreconditionFailedException {
        MemberEntity member = memberService.infoById(id);
        memberService.registerFcm(member, fcmToken);
    }

    @Transactional
    public void comeFromReport(String reason, Long id)  throws PreconditionFailedException {
        MemberEntity member = memberService.infoById(id);
        memberService.comeFromReport(member, reason);
    }

    @Transactional
    public void comeFromReview(int point, Long id)  throws PreconditionFailedException {
        MemberEntity member = memberService.infoById(id);
        memberService.comeFromReview(member, point);
    }

    @Transactional
    public void comeFromNoShow(Long id)  throws PreconditionFailedException {
        MemberEntity member = memberService.infoById(id);
        memberService.comeFromNoShow(member);
    }
}
