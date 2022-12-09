package com.example.demo.controller;

import com.example.demo.controllers.ItemController;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void test__getItems() {
        List<Item> list = mock(List.class);
        when(itemRepository.findAll()).thenReturn(list);
        ResponseEntity<List<Item>> items = itemController.getItems();
        assertEquals(list,items.getBody());
    }

    @Test
    public void test__getItemById() {
        Item item = mock(Item.class);
        when(itemRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(item));
        ResponseEntity<Item> itemById = itemController.getItemById(1L);
        assertEquals(item,itemById.getBody());
    }

    @Test
    public void test__getItemsByName() {
        List<Item> item = mock(List.class);
        when(itemRepository.findByName(anyString())).thenReturn(item);
        ResponseEntity<List<Item>> result = itemController.getItemsByName("test");
        assertEquals(item,result.getBody());
    }


}
