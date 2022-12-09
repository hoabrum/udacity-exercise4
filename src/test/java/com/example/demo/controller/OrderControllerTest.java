package com.example.demo.controller;

import com.example.demo.controllers.OrderController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderControllerMock;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void test__submit() {
        User user = mock(User.class);
        Cart cart = mock(Cart.class);
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(user.getCart()).thenReturn(cart);
        when(cart.getUser()).thenReturn(user);
        ResponseEntity<UserOrder> userOrder = orderControllerMock.submit("test");
        assertEquals(user,userOrder.getBody().getUser());
    }

    @Test
    public void test__getOrdersForUser() {
        User user = mock(User.class);
        List<UserOrder> list = mock(List.class);
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(orderRepository.findByUser(anyObject())).thenReturn(list);
        ResponseEntity<List<UserOrder>> userOrder = orderControllerMock.getOrdersForUser("test");
        assertEquals(list,userOrder.getBody());
    }
}
