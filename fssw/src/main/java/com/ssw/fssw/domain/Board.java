package com.ssw.fssw.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "BOARD")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_NUM")
    private Long num;
    @Column(name = "BOARD_TYPE")
    private char type;
    @Column(name = "BOARD_TITLE")
    private String title;
    @Column(name = "BOARD_NICK")
    private String nick;
    @Column(name = "BOARD_CONTENT")
    private String content;
    @Column(name = "BOARD_CATEGORY")
    private char category;
}
