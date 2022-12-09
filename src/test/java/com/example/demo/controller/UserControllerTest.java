package com.example.demo.controller;

import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @InjectMocks
    UserController userControllerMock;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void test__findById() {
        User user = mock(User.class);
        Optional<User> userOptional = Optional.of(user);
        when(user.getId()).thenReturn(1L);
        when(user.getUsername()).thenReturn("test");
        when(userRepository.findById(anyLong())).thenReturn(userOptional);
        ResponseEntity<User> userResponseEntity = userControllerMock.findById(1L);
        assertEquals("test", userResponseEntity.getBody().getUsername());
        assertEquals(1L, userResponseEntity.getBody().getId());
    }

    @Test
    public void test__findByUserName() {
        User user = mock(User.class);
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        ResponseEntity<User> userResponseEntity = userControllerMock.findByUserName("test");
        assertEquals(user, userResponseEntity.getBody());
    }

    @Test
    public void test__createUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("test");
        request.setPassword("123456789");
        request.setConfirmPassword("123456789");
        ResponseEntity<User> userResponseEntity = userControllerMock.createUser(request);
        assertEquals("test", userResponseEntity.getBody().getUsername());
    }
}
