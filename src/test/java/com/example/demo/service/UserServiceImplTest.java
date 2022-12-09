package com.example.demo.service;

import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceMock;

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void test__loadUserByUserName() {
        User user = mock(User.class);
        when(user.getUsername()).thenReturn("test");
        when(user.getPassword()).thenReturn("abcd123");
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        UserDetails userDetails = userServiceMock.loadUserByUsername("test");
        assertEquals(user.getUsername(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    public void test__getUserByUserName() {
        User user = mock(User.class);
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        User userDetails = userServiceMock.getUserDetailsByUserName("test");
        assertEquals(user, userDetails);
    }
}
