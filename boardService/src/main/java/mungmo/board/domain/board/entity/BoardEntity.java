package mungmo.board.domain.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import mungmo.board.domain.board.external.BoardDto;
import mungmo.board.domain.board.external.DogBoardDto;
import mungmo.board.domain.embede.FileInfo;
import mungmo.board.domain.board.external.DogInfoDto;
import mungmo.board.response.member.entity.MemberEntity;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "board")
public class BoardEntity {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;

    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    /**
     *  FREE, FUN, DOG, REVIEW
     *  자유, 유머, 강아지자랑, 번개후기
     */
    private Subject subject;

//    @OneToMany(mappedBy = "board")
    @OneToMany
    @JoinColumn(name = "comment_id")
    private List<CommentEntity> boardComments;

    @OneToOne
    @Getter
    private MemberEntity writer;

    @Embedded
    private FileInfo fileInfo;

    public BoardEntity() {
    }

    private BoardEntity(BoardDto boardDTO, MemberEntity member) {
        this.title = boardDTO.getTitle();
        this.content = boardDTO.getContent();
        this.subject = boardDTO.getSubject();
        this.createDate = LocalDateTime.now();
        if(Optional.ofNullable(boardDTO.getFile_path()).isPresent()) {
            fileInfo = new FileInfo(boardDTO.getOriginal_name(), boardDTO.getMask_name(), boardDTO.getFile_path(), boardDTO.getFile_type());
        } else {
            fileInfo = new FileInfo();
        }
        this.writer = member;
    }

    public static BoardEntity of(BoardDto boardDTO, MemberEntity member) {
        return new BoardEntity(boardDTO, member);
    }

    public void modify(BoardDto dto) {
        if(StringUtils.hasText(dto.getTitle())) this.title = dto.getTitle();
        if(StringUtils.hasText(dto.getContent())) this.content = dto.getContent();
        if(dto.getSubject() != null) this.subject = dto.getSubject();
        this.updateDate = LocalDateTime.now();
    }

    public DogInfoDto toDogInfoDTO() {
        return DogInfoDto.builder()
                .id(writer.getId())
                .dogName(writer.getDogName())
                .file_path(writer.getFileInfo().getFile_path())
                .original_name(writer.getFileInfo().getOriginal_name())
                .mask_name(writer.getFileInfo().getMask_name())
                .file_type(writer.getFileInfo().getFile_type())
                .build();
    }

    public DogBoardDto toDogBoardDto() {
        return DogBoardDto.builder()
                .board(this.toDto())
                .dogInfo(this.toDogInfoDTO())
                .build();
    }

    public BoardDto toDto() {
        return Optional.ofNullable(fileInfo).isPresent()
                ? BoardDto.builder()
                        .id(id)
                        .title(title)
                        .content(content)
                        .create_date(createDate)
                        .update_date(updateDate)
                        .subject(subject)
                        .original_name(fileInfo.getOriginal_name())
                        .mask_name(fileInfo.getMask_name())
                        .file_path(fileInfo.getFile_path())
                        .file_type(fileInfo.getFile_type())
                        .build()
                : BoardDto.builder()
                        .id(id)
                        .title(title)
                        .content(content)
                        .create_date(createDate)
                        .update_date(updateDate)
                        .subject(subject)
                        .build();
    }
}
