package com.ffirechess.service.implementation;

import com.ffirechess.io.entity.GameEntity;
import com.ffirechess.io.entity.UserEntity;
import com.ffirechess.io.repositories.GameRepository;
import com.ffirechess.io.repositories.UserRepository;
import com.ffirechess.service.GamesService;
import com.ffirechess.shared.dto.GameDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GamesServiceImp implements GamesService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;

    @Override
    public List<GameDto> getUserGames(String userId) {
        List<GameDto> returnValue = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<GameEntity, GameDto>() {
            @Override
            protected void configure() {
                map().setWhitePlayer(source.getWhitePlayer().getId());
                map().setBlackPlayer(source.getBlackPlayer().getId());
            }
        });

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) return returnValue;

        Iterable<GameEntity> playerGames = gameRepository.findAllUserGames(userEntity.getId());
        for (GameEntity gamesEntity : playerGames) {
            returnValue.add(modelMapper.map(gamesEntity, GameDto.class));
        }

        return returnValue;
    }

    @Override
    public GameDto getGame(String gameId) {
        GameDto returnValue = null;

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<GameEntity, GameDto>() {
            @Override
            protected void configure() {
                map().setWhitePlayer(source.getWhitePlayer().getId());
                map().setBlackPlayer(source.getBlackPlayer().getId());
            }
        });

        GameEntity gameEntity = gameRepository.findByGameId(gameId);

        if (gameEntity != null) {
            returnValue = modelMapper.map(gameEntity, GameDto.class);
        }

        return returnValue;
    }
}
