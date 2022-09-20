package com.ssw.fssw.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jfr.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="Community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Community_code")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Community_user_id")
    private Account Account;

    @Column(name="Community_number")
    private int num;

    @Column(name="Community_category")
    private String category;

    @Column(name="Community_title",length = 20)
    private String title;

    @Column(name="Community_content",length = 1000)
    private String contents;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd 'T' HH:mm:ss", timezone = "Asia/Seoul")
    @Column(name="Community_date")
    private LocalDateTime date = LocalDateTime.now();

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}