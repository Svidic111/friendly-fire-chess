package com.ffirechess.service.implementation;

import com.ffirechess.exceptions.UserServiceException;
import com.ffirechess.io.entity.UserEntity;
import com.ffirechess.io.repositories.UserRepository;
import com.ffirechess.shared.AmazonSES;
import com.ffirechess.shared.Utils;
import com.ffirechess.shared.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImpTest {
    @Mock
    UserRepository userRepository;

    @Mock
    Utils utils;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    AmazonSES amazonSES;

    @InjectMocks
    UserServiceImp userService;

    String userId = "IIuceb773bd";
    String encryptedPassword = "jhbsrv775sfuv";
    UserEntity userEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        userEntity = new UserEntity();
        userEntity.setId(123L);
        userEntity.setEncryptedPassword(encryptedPassword);
        userEntity.setNick("Svidic");
        userEntity.setUserId(userId);
        userEntity.setEmail("test@mail.com");
        userEntity.setEmailVerificationToken("zdrhdf554dh");
//        userEntity.setEmailVerificationStatus(true);
    }

    @Test
    void testGetUser() {


        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

        UserDto userDto = userService.getUser("test@email.com");

        assertNotNull(userDto);
        assertEquals("Svidic", userDto.getNick());
    }

    @Test
    final void getUser_UsernameNotFoundException() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class,
                      () -> { userService.getUser("test@email.com"); });
    }

    @Test
    final void testCreateUser_UserServiceException() {
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

        UserDto userDto = new UserDto();
        userDto.setPassword("jhbsrv775sfuv");
        userDto.setEmail("test@email.com");
        userDto.setNick("Svidic");

        assertThrows(UserServiceException.class,
                () -> { userService.createUser(userDto); });
    }

    @Test
    final void testCreateUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(utils.generateUserId(anyInt())).thenReturn(userId);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        Mockito.doNothing().when(amazonSES).verifyEmail(any(UserDto.class));

        UserDto userDto = new UserDto();
        userDto.setPassword("jhbsrv775sfuv");
        UserDto storedUserDetails = userService.createUser(userDto);

        assertNotNull(storedUserDetails);
        assertEquals(userEntity.getUserId(), storedUserDetails.getUserId());
        assertEquals(userEntity.getNick(), storedUserDetails.getNick());
        verify(bCryptPasswordEncoder, times(1)).encode("jhbsrv775sfuv");
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }
}