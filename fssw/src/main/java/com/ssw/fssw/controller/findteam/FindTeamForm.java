package com.ssw.fssw.controller.findteam;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Setter
@Getter
public class FindTeamForm {

    private String title;

    private String content;

    private int category;

    private int findTeam_num = 2;

    private LocalDateTime localDateTime;
}
