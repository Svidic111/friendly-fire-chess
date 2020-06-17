package com.ffirechess.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity(name="games")
public class GameEntity implements Serializable{

    private final static long serialVersionUID = -3114378606543425205L;

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 30, nullable = false)
    private String gameId;

    @Column(length = 1000, nullable = true)
    private String movesPGN;

    @Column(nullable = false)
    private Date datePlayed;

    @ManyToOne
    @JoinColumn(name = "whitePlayer", insertable = false, updatable = false)
    private UserEntity whitePlayer;

    @Column(nullable = false)
    private int whitePlRating;

    @Column(nullable = false)
    private String whitePlayerNickName;

    @ManyToOne
    @JoinColumn(name = "blackPlayer", insertable = false, updatable = false)
    private UserEntity blackPlayer;

    @Column(nullable = false)
    private int blackPlRating;

    @Column(nullable = false)
    private String blackPlayerNickName;

    @Column(length = 30, nullable = false)
    private String winnerNick;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getMovesPGN() {
        return movesPGN;
    }

    public void setMovesPGN(String movesPGN) {
        this.movesPGN = movesPGN;
    }

    public Date getDatePlayed() {
        return datePlayed;
    }

    public void setDatePlayed(Date datePlayed) {
        this.datePlayed = datePlayed;
    }

    public UserEntity getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(UserEntity whitePlayer) {
        this.whitePlayer = whitePlayer;
    }

    public int getWhitePlRating() {
        return whitePlRating;
    }

    public void setWhitePlRating(int whitePlRating) {
        this.whitePlRating = whitePlRating;
    }

    public String getWhitePlayerNickName() {
        return whitePlayerNickName;
    }

    public void setWhitePlayerNickName(String whitePlayerNickName) {
        this.whitePlayerNickName = whitePlayerNickName;
    }

    public UserEntity getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(UserEntity blackPlayer) {
        this.blackPlayer = blackPlayer;
    }

    public int getBlackPlRating() {
        return blackPlRating;
    }

    public void setBlackPlRating(int blackPlRating) {
        this.blackPlRating = blackPlRating;
    }

    public String getBlackPlayerNickName() {
        return blackPlayerNickName;
    }

    public void setBlackPlayerNickName(String blackPlayerNickName) {
        this.blackPlayerNickName = blackPlayerNickName;
    }

    public String getWinnerNick() {
        return winnerNick;
    }

    public void setWinnerNick(String winnerNick) {
        this.winnerNick = winnerNick;
    }
}
