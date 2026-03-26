package com.mysite.extraclass.notice;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.extraclass.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeCommentService {

    private final NoticeCommentRepository commentRepository;
    private final NoticeService noticeService;

    public void create(String content, Integer noticeId) {
        NoticeComment comment = new NoticeComment();
        comment.setContent(content);
        comment.setDate(LocalDateTime.now());
        comment.setNotice(noticeService.getNoticeById(noticeId));
        commentRepository.save(comment);
    }

    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }

    public NoticeComment getComment(Integer id) {
        Optional<NoticeComment> op = commentRepository.findById(id);
        if (op.isPresent()) {
            return op.get();
        }
        throw new DataNotFoundException("댓글을 찾을 수 없습니다.");
    }

    public void update(NoticeComment comment) {
        commentRepository.save(comment);
    }
}
