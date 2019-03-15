package com.ffirechess.ui.model.response;

import java.util.List;

public class UserRest {

    private String userId;
    private String nick;
    private String email;
    private List<GamesRest> userGames;

    public List<GamesRest> getUserGames() {
        return userGames;
    }

    public void setUserGames(List<GamesRest> userGames) {
        this.userGames = userGames;
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
