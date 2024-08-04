package mungmo.board.response.member.infra.adapter;

import lombok.RequiredArgsConstructor;
import mungmo.board.response.member.entity.MemberEntity;
import mungmo.board.response.member.infra.MemberRepository;
import mungmo.board.response.member.infra.repository.SpringDataJpaMemberRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberAdapter implements MemberRepository {
    private final SpringDataJpaMemberRepository publicCodeRepository;

    @Override
    public Optional<MemberEntity> findById(Long id) {
        return publicCodeRepository.findById(id);
    }
}
