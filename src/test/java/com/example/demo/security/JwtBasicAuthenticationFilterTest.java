package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Date;

import static com.example.demo.security.Constant.TOKEN_PREFIX;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JwtBasicAuthenticationFilterTest {

    @InjectMocks
    JwtBasicAuthenticationFilter jwtBasicAuthenticationFilter;

    @Mock
    Environment environment;

    @Mock
    AuthenticationManager authenticationManager;

    @Test
    public void testDoFilterInternal() throws IOException, ServletException {
        MockHttpServletRequest httpServletRequest = mock(MockHttpServletRequest.class);
        MockHttpServletResponse httpServletResponse = mock(MockHttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(environment.getProperty("authorization.token.header.name")).thenReturn("Authorization");
        when(environment.getProperty("token.secret")).thenReturn("changeit");
        String token = Jwts.builder()
                .setSubject(String.valueOf(1))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong("86400")))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
                .compact();

        when(httpServletRequest.getHeader(anyString())).thenReturn(TOKEN_PREFIX + token);
        jwtBasicAuthenticationFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);
        verify(filterChain, times(1)).doFilter(httpServletRequest, httpServletResponse);
    }
}
