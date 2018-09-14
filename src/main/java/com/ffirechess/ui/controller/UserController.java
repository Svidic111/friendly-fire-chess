package com.ffirechess.ui.controller;

import com.ffirechess.service.UserService;
import com.ffirechess.shared.dto.UserDto;
import com.ffirechess.ui.model.request.UserDetaisRequestModel;
import com.ffirechess.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/users")    // http://localhost:8080/users
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getUser() {
        return "getUser was called";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetaisRequestModel userdDetails) {

        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userdDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    @PutMapping
    public  String updateUser() {

        return "updateUser was called";
    }

    @DeleteMapping
    public  String deleteUser() {
        return "deleteUser was called";
    }

}
