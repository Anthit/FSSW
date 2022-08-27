package com.ssw.fssw.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicInsert
@Getter
@Setter
@Table(name="Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_CODE")
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMENT_COMMUNITY_ID")
    private Community community;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMENT_USER_ID")
    private User user;

    @Column(name = "comment_text", length = 1000)
    public String text;

    @ColumnDefault("1")
    @Column(name="comment_group")
    public int group;

    @ColumnDefault("1")
    @Column(name="comment_class")
    public int floor;

    @ColumnDefault("1")
    @Column(name="comment_order")
    public int order;

}

