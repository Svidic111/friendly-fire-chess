package com.ffirechess.ui.controller;

import com.ffirechess.service.UserService;
import com.ffirechess.service.implementation.UserServiceImp;
import com.ffirechess.shared.dto.UserDto;
import com.ffirechess.ui.model.response.UserRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserControllerTest {


    private static final String USER_ID = "jjfe66840nfh74";


    @Mock
    UserServiceImp userService;

    @Mock
    UserDto userDto;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        userDto = new UserDto();
//        userDto.setEmail("test101@mail.com");
        userDto.setNick("testNick101");
        userDto.setUserId(USER_ID);
        userDto.setEncryptedPassword("jbdvb99562vads62");
        userDto.setRating(1350);
    }

    @Test
    final void testGetUser() {
        when(userService.getUserByUserId(anyString())).thenReturn(userDto);

        UserRest userRest = userController.getUser(USER_ID);

        assertNotNull(userRest);
        assertEquals(USER_ID, userRest.getUserId());
    }
}