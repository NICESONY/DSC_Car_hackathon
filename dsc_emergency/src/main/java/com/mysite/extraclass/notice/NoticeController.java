package com.mysite.extraclass.notice;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class NoticeController {

    private final NoticeService noticeService;

    @Value("${cloud.aws.s3.endpoint}")
    private String downpath;

    @GetMapping("/notice")
    public String showNotices(Model model) {
        model.addAttribute("noticeList", noticeService.findAllNotices());
        return "notice/notice";
    }

    @GetMapping("/notice/add")
    public String addNotice() {
        return "notice/addnotice2";
    }

    @PostMapping("/notice/create")
    public String createNotice(@ModelAttribute Notice notice,
                               @RequestParam("file1") MultipartFile file1,
                               @RequestParam("file2") MultipartFile file2,
                               @RequestParam("file3") MultipartFile file3) throws IOException {
        noticeService.createNotice(notice, file1, file2, file3);
        return "redirect:/notice";
    }

    @GetMapping("/notice/detail/{id}")
    public String showNotice(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("notice", noticeService.getNoticeById(id));
        model.addAttribute("downpath", "https://" + downpath);
        return "notice/noticedetail2";
    }

    @GetMapping("/notice/detail/delete/{id}")
    public String deleteNotice(@PathVariable("id") Integer id) {
        noticeService.deleteNotice(id);
        return "redirect:/notice";
    }

    @GetMapping("/update2/{id}")
    public String editNotice(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("notice", noticeService.getNoticeById(id));
        return "notice/noticefix2";
    }

    @PostMapping("/notice/update2")
    public String updateNotice(@ModelAttribute Notice notice,
                               @RequestParam("file1") MultipartFile file1,
                               @RequestParam("file2") MultipartFile file2,
                               @RequestParam("file3") MultipartFile file3) throws IOException {
        noticeService.updateNotice(notice, file1, file2, file3);
        return "redirect:/notice";
    }
}
