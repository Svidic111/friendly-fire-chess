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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<GameDto> getUserGames(String userId, int page, int limit) {
        List<GameDto> returnValue = new ArrayList<>();

        if (page > 0) page -= 1;

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<GameEntity, GameDto>() {
            @Override
            protected void configure() {
                map().setWhitePlayer(source.getWhitePlayer().getId());
                map().setBlackPlayer(source.getBlackPlayer().getId());
            }
        });

        Pageable pageableRequest = PageRequest.of(page, limit);

        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) return returnValue;

        Page<GameEntity> gamesPage = gameRepository.findAllUserGames(userEntity.getId(), pageableRequest);

        List<GameEntity> playerGames = gamesPage.getContent();

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
