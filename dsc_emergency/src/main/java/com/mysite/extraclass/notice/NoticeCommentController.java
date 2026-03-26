package com.mysite.extraclass.notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class NoticeCommentController {

    private final NoticeCommentService commentService;

    @PostMapping("/comment2/create/{id}")
    public String createComment(@RequestParam("content") String content,
                                @PathVariable("id") Integer id) {
        commentService.create(content, id);
        return "redirect:/notice/detail/" + id;
    }

    @GetMapping("/comment2/delete/{nid}/{cid}")
    public String deleteComment(@PathVariable("nid") Integer nid,
                                @PathVariable("cid") Integer cid) {
        commentService.delete(cid);
        return "redirect:/notice/detail/" + nid;
    }

    @GetMapping("/comment2/update2/{id}")
    public String editComment(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("comment2", commentService.getComment(id));
        return "notice/commentfix2";
    }

    @PostMapping("/comment2/update2/{id}")
    public String updateComment(@RequestParam("content") String content,
                                @PathVariable("id") Integer id) {
        NoticeComment comment = commentService.getComment(id);
        comment.setContent(content);
        commentService.update(comment);
        return "redirect:/notice/detail/" + comment.getNotice().getId();
    }
}
