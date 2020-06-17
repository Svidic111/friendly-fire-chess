package com.ffirechess.ui.controller;

import com.ffirechess.websocket.GameEndPoint;
import com.ffirechess.service.GamesService;
import com.ffirechess.service.UserService;
import com.ffirechess.shared.dto.GameDto;
import com.ffirechess.shared.dto.UserDto;
import com.ffirechess.ui.model.response.GameRest;
import com.ffirechess.ui.model.response.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//@SpringBootApplication
@RestController
@RequestScope
@RequestMapping("/games")
public class GameController {

    @Autowired
    UserService userService;

    @Autowired
    GamesService gamesService;


    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, "application/hal+json"})
    public Resource<GameRest> getGame(@PathVariable String id) {

        GameDto gameDto = gamesService.getGame(id);

        UserDto whitePlayer = userService.getUserById(gameDto.getWhitePlayer());
        UserDto blackPlayer = userService.getUserById(gameDto.getBlackPlayer());

        ModelMapper modelMapper = new ModelMapper();

        Link whitePlayerLink = linkTo(methodOn(UserController.class).getUser(whitePlayer.getUserId())).withRel(whitePlayer.getNick());
        Link blackPlayerLink = linkTo(methodOn(UserController.class).getUser(blackPlayer.getUserId())).withRel(blackPlayer.getNick());

        GameRest gameRestModel = modelMapper.map(gameDto, GameRest.class);
        gameRestModel.add(whitePlayerLink);
        gameRestModel.add(blackPlayerLink);

        return new Resource<>(gameRestModel);
    }



}
