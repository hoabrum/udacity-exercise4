package com.example.demo.model;

import com.example.demo.model.persistence.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ItemTest {

    @Test
    public void testItem() {
        BigDecimal bigDecimal = mock(BigDecimal.class);
        Item item = new Item();
        item.setId(1L);
        item.setDescription("test");
        item.setName("testName");
        item.setPrice(bigDecimal);
        assertEquals(1L, item.getId().longValue());
        assertEquals("test", item.getDescription());
        assertEquals("testName", item.getName());
        assertEquals(bigDecimal, item.getPrice());
    }
}
