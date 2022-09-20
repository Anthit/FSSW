package com.ssw.fssw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private Long num;

    @Column(name = "email")
    private String email;

    @Column(name = "pw")
    private String pw;

    @Column(name = "nick")
    private String nick;

    @JsonIgnore
    @OneToMany(mappedBy = "Account", cascade = CascadeType.ALL)
    private List<Community> communityList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "Account", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();
}
