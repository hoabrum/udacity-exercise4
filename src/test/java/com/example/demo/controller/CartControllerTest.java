package com.example.demo.controller;

import com.example.demo.controllers.CartController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

    @InjectMocks
    private CartController cartControllerMock;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void test__addTocart() {
        ModifyCartRequest modifyCartRequest = mock(ModifyCartRequest.class);
        User user = mock(User.class);
        Item item = mock(Item.class);
        Cart cart = mock(Cart.class);
        when(modifyCartRequest.getUsername()).thenReturn("test");
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(itemRepository.findById(anyLong())).thenReturn(Optional.of(item));
        when(user.getCart()).thenReturn(cart);
        ResponseEntity<Cart> cartResponseEntity = cartControllerMock.addTocart(modifyCartRequest);
        assertEquals(cart, cartResponseEntity.getBody());
    }

    @Test
    public void test__removeTocart() {
        ModifyCartRequest modifyCartRequest = mock(ModifyCartRequest.class);
        User user = mock(User.class);
        Item item = mock(Item.class);
        Cart cart = mock(Cart.class);
        when(modifyCartRequest.getUsername()).thenReturn("test");
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(itemRepository.findById(anyLong())).thenReturn(Optional.of(item));
        when(user.getCart()).thenReturn(cart);
        ResponseEntity<Cart> cartResponseEntity = cartControllerMock.removeFromcart(modifyCartRequest);
        assertEquals(cart, cartResponseEntity.getBody());
    }
}
