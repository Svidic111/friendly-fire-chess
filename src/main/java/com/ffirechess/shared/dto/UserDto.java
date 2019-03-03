package com.ffirechess.shared.dto;

import com.ffirechess.io.entity.BlackPlayerGamesEntity;
import com.ffirechess.io.entity.WhitePlayerGamesEntity;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {


    private static final long serialVersionUID = -6015378606543471205L;
    private long id;
    private String userId;
    private String nick;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus = false;
    private List<WhitePlayerGamesEntity> gamesAsWhite;
    private List<BlackPlayerGamesEntity> gamesAsBlack;

    public List<WhitePlayerGamesEntity> getGamesAsWhite() {
        return gamesAsWhite;
    }

    public void setGamesAsWhite(List<WhitePlayerGamesEntity> gamesAsWhite) {
        this.gamesAsWhite = gamesAsWhite;
    }

    public List<BlackPlayerGamesEntity> getGamesAsBlack() {
        return gamesAsBlack;
    }

    public void setGamesAsBlack(List<BlackPlayerGamesEntity> gamesAsBlack) {
        this.gamesAsBlack = gamesAsBlack;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public Boolean getEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }
}
