package com.ffirechess.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "users")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 3703096938112651465L;

    @Id
    @GeneratedValue // will be generated by framework automaticly
    private long id;

    @Column(nullable = false) // "nullable = false" means that this column can't contain null
    private String userId;

    @Column(nullable = false, length = 40)
    private String nick;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String encryptedPassword;

    private String emailVerificationToken;

    @Column(nullable = false)
    private Boolean emailVerificationStatus = false;

    @Column(nullable = false)
    private int rating;

    @OneToMany(mappedBy = "whitePlayer", cascade=CascadeType.ALL)
    private List<GameEntity> gamesForWhite;

    @OneToMany(mappedBy = "blackPlayer", cascade=CascadeType.ALL)
    private List<GameEntity> gamesForBlack;

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<GameEntity> getGamesForWhite() {
        return gamesForWhite;
    }

    public void setGamesForWhite(List<GameEntity> gamesForWhite) {
        this.gamesForWhite = gamesForWhite;
    }

    public List<GameEntity> getGamesForBlack() {
        return gamesForBlack;
    }

    public void setGamesForBlack(List<GameEntity> gamesForBlack) {
        this.gamesForBlack = gamesForBlack;
    }
}
