package com.ssw.fssw.domain;


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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="Community_user_id")
    private User user;

    @Column(name="Community_number")
    private int num;

    @Column(name="Community_category")
    private int category;

    @Column(name="Community_title",length = 20)
    private String title;

    @Column(name="Community_content",length = 1000)
    private String contents;

    @Column(name="Community_date")
    private LocalDateTime date;

    @OneToMany(mappedBy = "community")
    private List<Comment> comments = new ArrayList<>();
}