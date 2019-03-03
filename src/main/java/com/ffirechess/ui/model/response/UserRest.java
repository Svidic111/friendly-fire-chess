package com.ffirechess.ui.model.response;

import java.util.List;

public class UserRest {

    private String userId;
    private String nick;
    private String email;
    private List<WhitePlayaerGamesRest> whitePlayaerGames;
    private List<BlackPlayaerGamesRest> blackPlayaerGames;

    public List<WhitePlayaerGamesRest> getWhitePlayaerGames() {
        return whitePlayaerGames;
    }

    public void setWhitePlayaerGames(List<WhitePlayaerGamesRest> whitePlayaerGames) {
        this.whitePlayaerGames = whitePlayaerGames;
    }

    public List<BlackPlayaerGamesRest> getBlackPlayaerGames() {
        return blackPlayaerGames;
    }

    public void setBlackPlayaerGames(List<BlackPlayaerGamesRest> blackPlayaerGames) {
        this.blackPlayaerGames = blackPlayaerGames;
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
}
