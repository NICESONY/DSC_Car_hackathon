package com.mysite.extraclass.notice;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.extraclass.DataNotFoundException;
import com.mysite.extraclass.S3Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final S3Service s3Service;

    public void createNotice(Notice notice, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        if (file1 != null && !file1.isEmpty()) {
            s3Service.uploadFile(file1, file1.getOriginalFilename());
            notice.setImage1(file1.getOriginalFilename());
        }
        if (file2 != null && !file2.isEmpty()) {
            s3Service.uploadFile(file2, file2.getOriginalFilename());
            notice.setImage2(file2.getOriginalFilename());
        }
        if (file3 != null && !file3.isEmpty()) {
            s3Service.uploadFile(file3, file3.getOriginalFilename());
            notice.setImage3(file3.getOriginalFilename());
        }

        notice.setDate(LocalDateTime.now());
        this.noticeRepository.save(notice);
    }

    public List<Notice> findAllNotices() {
        return noticeRepository.findAll();
    }

    public Notice getNoticeById(Integer id) {
        Optional<Notice> op = this.noticeRepository.findById(id);
        if (op.isPresent()) {
            return op.get();
        }
        throw new DataNotFoundException("공지사항을 찾을 수 없습니다.");
    }

    public void deleteNotice(Integer id) {
        noticeRepository.deleteById(id);
    }

    public void updateNotice(Notice notice, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        if (file1 != null && !file1.isEmpty()) {
            s3Service.uploadFile(file1, file1.getOriginalFilename());
            notice.setImage1(file1.getOriginalFilename());
        }
        if (file2 != null && !file2.isEmpty()) {
            s3Service.uploadFile(file2, file2.getOriginalFilename());
            notice.setImage2(file2.getOriginalFilename());
        }
        if (file3 != null && !file3.isEmpty()) {
            s3Service.uploadFile(file3, file3.getOriginalFilename());
            notice.setImage3(file3.getOriginalFilename());
        }

        noticeRepository.save(notice);
    }
}
