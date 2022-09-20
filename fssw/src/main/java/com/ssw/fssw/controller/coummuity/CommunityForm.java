package com.ssw.fssw.controller.coummuity;

import com.ssw.fssw.domain.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class CommunityForm {


    private String title;

    private String content;

    private String category;

    private int board_num = 1;

    private LocalDateTime localDateTime;
}
