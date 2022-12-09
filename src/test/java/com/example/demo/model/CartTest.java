package com.example.demo.model;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class CartTest {

    @Test
    public void testCart() {
        List list = mock(List.class);
        BigDecimal bigDecimal = mock(BigDecimal.class);
        User user = mock(User.class);
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setItems(list);
        cart.setTotal(bigDecimal);
        cart.setUser(user);
        assertEquals(1L, cart.getId().longValue());
        assertEquals(list, cart.getItems());
        assertEquals(bigDecimal, cart.getTotal());
        assertEquals(user, cart.getUser());

    }
}
