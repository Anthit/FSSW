package com.ssw.fssw.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long id;

    @Column(name="USER_EMAIL_ID")
    private String emailId;

    @Column(name="USER_NICKNAME")
    private String nickname;

    @Column(name="USER_PASSWORD")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Community> communityList = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();


}
