package com.ssw.fssw.controller.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {
    private Long id;
    private int order;
    private int group;
    private String text;

    private String reText = "삭제된 댓글 입니다.";
}
