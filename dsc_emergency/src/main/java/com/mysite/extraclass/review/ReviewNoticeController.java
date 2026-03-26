package com.mysite.extraclass.review;

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
public class ReviewNoticeController {

    private final ReviewNoticeService reviewService;

    @Value("${cloud.aws.s3.endpoint}")
    private String downpath;

    @GetMapping("/review")
    public String showReviews(Model model) {
        model.addAttribute("noticeList", reviewService.findAllReviews());
        return "review/review";
    }

    @GetMapping("/review/add")
    public String addReview() {
        return "review/addnotice1";
    }

    @PostMapping("/review/create")
    public String createReview(@ModelAttribute ReviewNotice review,
                               @RequestParam("file1") MultipartFile file1,
                               @RequestParam("file2") MultipartFile file2,
                               @RequestParam("file3") MultipartFile file3) throws IOException {
        reviewService.createReview(review, file1, file2, file3);
        return "redirect:/review";
    }

    @GetMapping("/review/detail/{id}")
    public String showReview(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("review", reviewService.getReviewById(id));
        model.addAttribute("downpath", "https://" + downpath);
        return "review/noticedetail1";
    }

    @GetMapping("/review/detail/delete/{id}")
    public String deleteReview(@PathVariable("id") Integer id) {
        reviewService.deleteReview(id);
        return "redirect:/review";
    }

    @GetMapping("/update/{id}")
    public String editReview(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("review", reviewService.getReviewById(id));
        return "review/noticefix1";
    }

    @PostMapping("/review/update")
    public String updateReview(@ModelAttribute ReviewNotice review,
                               @RequestParam("file1") MultipartFile file1,
                               @RequestParam("file2") MultipartFile file2,
                               @RequestParam("file3") MultipartFile file3) throws IOException {
        reviewService.updateReview(review, file1, file2, file3);
        return "redirect:/review";
    }

    @GetMapping("/review/searchkw")
    public String searchByKeyword(Model model, @RequestParam("kw") String kw) {
        model.addAttribute("noticeList", reviewService.findByTitle(kw));
        return "review/review";
    }
}
