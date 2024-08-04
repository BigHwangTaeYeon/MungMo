package mungMo.member.domain.member.service;

import lombok.RequiredArgsConstructor;
import mungMo.member.com.config.ResponseMessage;
import mungMo.member.com.exception.FileUploadException;
import mungMo.member.com.exception.PreconditionFailedException;
import mungMo.member.com.util.Upload;
import mungMo.member.domain.embede.FileInfo;
import mungMo.member.domain.member.infra.MemberRepository;
import mungMo.member.domain.member.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Value("${api.upload.dir.dog}")
    private String uploadDir;

    @Transactional(readOnly = true)
    public MemberEntity infoById(Long id) throws PreconditionFailedException {
        return memberRepository.findById(id)
                .orElseThrow(() -> new PreconditionFailedException(ResponseMessage.PRECONDITIONFAILED.getMessage()));
    }

    @Transactional
    public void registerNickname(String dogName, MemberEntity member) {
        member.changeDogName(dogName);
    }

    @Transactional
    public void updateDogImg(MemberEntity member, MultipartFile multipartFile) throws FileUploadException {
        if(!ObjectUtils.isEmpty(multipartFile)) {
            Map<String, String> upload = new Upload(uploadDir, multipartFile).uploadImage();
            FileInfo fileInfo = new FileInfo(upload, "img");
            member.fileInfoInstance(fileInfo);
        }
    }

    @Transactional
    public void dogLike(MemberEntity member, String like) {
        member.changeDogLike(like);
    }

    @Transactional
    public void registerFcm(MemberEntity member, String fcmToken) {
        member.getFcmToken().changeToken(fcmToken);
    }

    @Transactional
    public void comeFromReport(MemberEntity member, String reason) {
        member.comeFromReport(reason);
    }

    @Transactional
    public void comeFromReview(MemberEntity member, int point) {
        member.comeFromReview(point);
    }

    @Transactional
    public void comeFromNoShow(MemberEntity member) {
        member.comeFromNoShow();
    }
}
