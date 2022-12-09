package com.example.demo.security;

import com.example.demo.model.requests.LoginRequestModel;
import com.example.demo.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import javax.servlet.FilterChain;
import java.io.ByteArrayInputStream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JwtUserPasswordAuthenticationFilterTest {

    @InjectMocks
    JwtUserPasswordAuthenticationFilter jwtUserPasswordAuthenticationFilter;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private Environment environment;

    @Mock
    AuthenticationManager authenticationManager;

    @Test
    public void testAttemptAuthentication() throws JsonProcessingException {
        MockHttpServletRequest httpServletRequest = mock(MockHttpServletRequest.class);
        LoginRequestModel loginRequestModel = new LoginRequestModel();
        loginRequestModel.setPassword("1234567");
        loginRequestModel.setUserName("test");
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = objectMapper.writeValueAsBytes(loginRequestModel);
        when(httpServletRequest.getInputStream()).thenReturn(new DelegatingServletInputStream(new ByteArrayInputStream(bytes)));
        MockHttpServletResponse httpServletResponse = mock(MockHttpServletResponse.class);
        jwtUserPasswordAuthenticationFilter.attemptAuthentication(httpServletRequest, httpServletResponse);
    }

    @Test
    public void testSuccessfulAuthentication() {
        MockHttpServletRequest httpServletRequest = mock(MockHttpServletRequest.class);
        MockHttpServletResponse httpServletResponse = mock(MockHttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        Authentication authentication = mock(Authentication.class);
        User user = mock(User.class);
        when(authentication.getPrincipal()).thenReturn(user);
        when(user.getUsername()).thenReturn("123456");
        com.example.demo.model.persistence.User userDetails = mock(com.example.demo.model.persistence.User.class);
        when(userService.getUserDetailsByUserName(anyString())).thenReturn(userDetails);
        when(userDetails.getId()).thenReturn(1L);
        when(environment.getProperty("token.expiration_time")).thenReturn("864000");
        when(environment.getProperty("token.secret")).thenReturn("changeit");
        jwtUserPasswordAuthenticationFilter.successfulAuthentication(httpServletRequest, httpServletResponse, filterChain, authentication);
    }
}
