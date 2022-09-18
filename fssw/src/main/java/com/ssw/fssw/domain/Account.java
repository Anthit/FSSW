package com.ssw.fssw.domain;

import javax.persistence.*;

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

    public Long getNum() {
        return num;
    }
    public String getEmail() {
        return email;
    }
    public String getPw() {
        return pw;
    }
    public String getNick() {
        return nick;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
