package com.ffirechess.ui.model.response;


import org.springframework.hateoas.ResourceSupport;

import java.sql.Date;

public class GameRest extends ResourceSupport{

    private String gameId;
    private String movesPGN;
    private Date datePlayed;
    private String whitePlayerNickName;
    private int whitePlRating;
    private String blackPlayerNickName;
    private int blackPlRating;
    private String winnerNick;


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

    public String getWhitePlayerNickName() {
        return whitePlayerNickName;
    }

    public void setWhitePlayerNickName(String whitePlayerNickName) {
        this.whitePlayerNickName = whitePlayerNickName;
    }

    public int getWhitePlRating() {
        return whitePlRating;
    }

    public void setWhitePlRating(int whitePlRating) {
        this.whitePlRating = whitePlRating;
    }

    public String getBlackPlayerNickName() {
        return blackPlayerNickName;
    }

    public void setBlackPlayerNickName(String blackPlayerNickName) {
        this.blackPlayerNickName = blackPlayerNickName;
    }

    public int getBlackPlRating() {
        return blackPlRating;
    }

    public void setBlackPlRating(int blackPlRating) {
        this.blackPlRating = blackPlRating;
    }

    public String getWinnerNick() {
        return winnerNick;
    }

    public void setWinnerNick(String winnerNick) {
        this.winnerNick = winnerNick;
    }
}

