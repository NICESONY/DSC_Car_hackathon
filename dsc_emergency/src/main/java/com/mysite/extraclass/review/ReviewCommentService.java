package com.mysite.extraclass.review;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.extraclass.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewCommentService {

    private final ReviewCommentRepository commentRepository;
    private final ReviewNoticeService reviewService;

    public void create(String content, Integer reviewId) {
        ReviewComment comment = new ReviewComment();
        comment.setContent(content);
        comment.setDate(LocalDateTime.now());
        comment.setReview(reviewService.getReviewById(reviewId));
        commentRepository.save(comment);
    }

    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }

    public ReviewComment getComment(Integer id) {
        Optional<ReviewComment> op = commentRepository.findById(id);
        if (op.isPresent()) {
            return op.get();
        }
        throw new DataNotFoundException("댓글을 찾을 수 없습니다.");
    }

    public void update(ReviewComment comment) {
        commentRepository.save(comment);
    }
}
