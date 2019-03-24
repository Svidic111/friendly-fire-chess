package com.ffirechess.service;

import com.ffirechess.shared.dto.GameDto;

import java.util.List;

public interface GamesService {

    List<GameDto> getUserGames(String userId);
    GameDto getGame(String gameId);

}
