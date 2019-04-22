package com.ffirechess.ui.controller;

import com.ffirechess.exceptions.UserServiceException;
import com.ffirechess.service.GamesService;
import com.ffirechess.service.UserService;
import com.ffirechess.shared.dto.GameDto;
import com.ffirechess.shared.dto.UserDto;
import com.ffirechess.ui.model.request.UserDetaisRequestModel;
import com.ffirechess.ui.model.response.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@SpringBootApplication
@RestController
@RequestMapping("/users")    // http://localhost:8080/users
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    GamesService gamesService;

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest getUser(@PathVariable String id) {
        UserRest userRestModel;

        UserDto userDto = userService.getUserByUserId(id);

        ModelMapper modelMapper = new ModelMapper();
        userRestModel = modelMapper.map(userDto, UserRest.class);

        Link userGamesLink = linkTo(methodOn(UserController.class).getUserGames(id, 1, 10)).withRel("userGames");
        userRestModel.add(userGamesLink);

        return userRestModel;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                 produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest createUser(@RequestBody UserDetaisRequestModel userdDetails) {

        UserRest returnValue = new UserRest();

        if (userdDetails.getNick().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(userdDetails, UserDto.class);

        UserDto createdUser = userService.createUser(userDto);
        returnValue = modelMapper.map(createdUser, UserRest.class);

        return returnValue;
    }

    @PutMapping(path = "/{id}",
                consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public  UserRest updateUser(@RequestBody UserDetaisRequestModel userdDetails, @PathVariable String id) {

        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userdDetails, userDto);

        UserDto updatedUser = userService.updateUser(id, userDto);
        BeanUtils.copyProperties(updatedUser, returnValue);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public OperationStatusModel deleteUser(@PathVariable String id) {

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        userService.deleteUser(id);
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return returnValue;
    }

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserRest> getUsers(@RequestParam(value="page", defaultValue="0") int page,
                                   @RequestParam(value="limit", defaultValue="10") int limit) {
        List<UserRest> returnValue = new ArrayList<>();

        List<UserDto> users = userService.getUsers(page, limit);
        for (UserDto userDto: users) {
            UserRest userModel = new UserRest();
            BeanUtils.copyProperties(userDto, userModel);
            returnValue.add(userModel);
        }

        return returnValue;
    }

    @GetMapping(path = "/{id}/games", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, "application/hal+json"})
    public Resources<GameRest> getUserGames(@PathVariable String id,
                                            @RequestParam(value="page", defaultValue="0") int page,
                                            @RequestParam(value="limit", defaultValue="10") int limit) {
        List<GameRest> gamesListRestModel = new ArrayList<>();

        List<GameDto> gamesDto = gamesService.getUserGames(id, page, limit);

        if (gamesDto != null && !gamesDto.isEmpty()) {
            Type listType = new TypeToken<List<GameRest>>() {}.getType();
            gamesListRestModel = new ModelMapper().map(gamesDto, listType);

            for (GameRest gameRest : gamesListRestModel) {
                Link userGameLink = linkTo(methodOn(UserController.class).getUserGame(gameRest.getGameId(), id)).withSelfRel();
                gameRest.add(userGameLink);
            }
        }

        return new Resources<>(gamesListRestModel);
    }

    @GetMapping(path = "/{id}/games/{gameId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, "application/hal+json"})
    public Resource<GameRest> getUserGame(@PathVariable String gameId, @PathVariable String id) {

        GameDto gameDto = gamesService.getGame(gameId);

        UserDto user = userService.getUserByUserId(id);

        long rivalId;
        if (gameDto.getWhitePlayer() == user.getId()) {
            rivalId = gameDto.getBlackPlayer();
        } else rivalId = gameDto.getWhitePlayer();

        UserDto rival = userService.getUserById(rivalId);
        String rivalUserId = rival.getUserId();

        ModelMapper modelMapper = new ModelMapper();

        Link userLink = linkTo(methodOn(UserController.class).getUser(id)).withRel(user.getNick());
        Link userGamesLink = linkTo(methodOn(UserController.class).getUserGames(id, 1, 10)).withRel("userGames");
        Link rivalLink = linkTo(methodOn(UserController.class).getUser(rivalUserId)).withRel(rival.getNick());

        GameRest gameRestModel = modelMapper.map(gameDto, GameRest.class);
        gameRestModel.add(userLink);
        gameRestModel.add(userGamesLink);
        gameRestModel.add(rivalLink);

        return new Resource<>(gameRestModel);
    }

    @GetMapping (path = "/email-verification", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public OperationStatusModel verifyEmailToken(@RequestParam(value = "token") String token) {

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());

        boolean isVerified = userService.verifyEmailToken(token);
        if (isVerified) {
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }
        else {
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
        }

        return  returnValue;
    }
}
