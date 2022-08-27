package com.ssw.fssw.controller.coummuity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommunityForm {
    private String title;
    private String content;
    private int category;
    private LocalDateTime localDateTime;
}
