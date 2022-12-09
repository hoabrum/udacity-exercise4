package com.example.demo.model;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    @Test
    public void testUser() {
        Cart cart = mock(Cart.class);
        User user = new User();
        user.setId(1L);
        user.setPassword("123456");
        user.setCart(cart);
        user.setUsername("username");
        assertEquals(1L, user.getId());
        assertEquals("123456", user.getPassword());
        assertEquals(cart, user.getCart());
        assertEquals("username", user.getUsername());
    }
}
