package com.ffirechess.shared.dto;


import java.sql.Date;

public class GameDto {
    private long id;
    private String gameId;
    private String movesPGN;
    private Date datePlayed;
    private long whitePlayer;
    private int whitePlRating;
    private String whitePlayerNickName;
    private long blackPlayer;
    private int blackPlRating;
    private String blackPlayerNickName;
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

    public long getWhitePlayer() {
        return whitePlayer;
    }

    public void setWhitePlayer(long whitePlayer) {
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

    public long getBlackPlayer() {
        return blackPlayer;
    }

    public void setBlackPlayer(long blackPlayer) {
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