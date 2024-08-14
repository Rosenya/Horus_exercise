package com.ArkadiaPlocienniczak.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class WallTest {

    @Mock
    private CompositeBlock compositeBlockMock;

    @Mock
    private Block redBlock;

    @InjectMocks
    private Wall wall;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        List<Block> blocks = new ArrayList<>();

        blocks.add(redBlock);
        when(compositeBlockMock.getBlocks()).thenReturn(blocks);

        wall = new Wall(compositeBlockMock);
    }
    @Test
    void testFindBlockByColorIfFound() {
        //given
        when(redBlock.getColor()).thenReturn("Red");
        when(redBlock.getMaterial()).thenReturn("Material1");

        //when
        Optional<Block> result = wall.findBlockByColor("Red");

        //then
        assertTrue(result.isPresent(), "Block should be found");
        assertEquals("Red", result.get().getColor(), "Color should match");
    }

    @Test
    void testFindBlockByColorIfNotFound() {
        //given
        when(redBlock.getColor()).thenReturn("Red");
        when(redBlock.getMaterial()).thenReturn("Material1");
        List<Block> blocks = new ArrayList<>();
        when(compositeBlockMock.getBlocks()).thenReturn(blocks);

        //when
        Optional<Block> result = wall.findBlockByColor("Green");

        //then
        assertTrue(result.isEmpty(), "Block not found");
    }

    @Test
    public void testFindBlockByColorIfListIsEmpty() {
        //given
        List<Block> emptyBlockList = new ArrayList<>();
        when(compositeBlockMock.getBlocks()).thenReturn(emptyBlockList);

        wall = new Wall(compositeBlockMock);

        //when
        Optional<Block> result = wall.findBlockByColor("Green");

        //then
        assertTrue(result.isEmpty(), "Result should be Optional.empty() when the list is empty");
    }

    @Test
    void testFindBlocksMaterialIfFound() {
        //given
        when(redBlock.getColor()).thenReturn("Red");
        when(redBlock.getMaterial()).thenReturn("Material1");

        List<Block> blocks = new ArrayList<>();
        blocks.add(redBlock);
        when(compositeBlockMock.getBlocks()).thenReturn(blocks);

        wall = new Wall(compositeBlockMock);

        //when
        List<Block> result = wall.findBlocksByMaterial("Material1");

        //then
        assertTrue(result.contains(redBlock), "Block with specified material should be found");
        assertEquals("Material1", result.get(0).getMaterial(), "Material of the found block should match");
    }

    @Test
    void testFindBlocksByMaterialIfNotFound() {
        //given
        when(redBlock.getColor()).thenReturn("Red");
        when(redBlock.getMaterial()).thenReturn("Material1");

        List<Block> blocks = new ArrayList<>();
        blocks.add(redBlock);
        when(compositeBlockMock.getBlocks()).thenReturn(blocks);

        wall = new Wall(compositeBlockMock);

        //when
        List<Block> result = wall.findBlocksByMaterial("Material2");

        //then
        assertTrue(result.isEmpty(), "List should be empty when no block with the specified material is found");
    }

    @Test
    void testFindBlocksByMaterialIfEmpty() {
        //given
        List<Block> emptyBlockList = new ArrayList<>();
        when(compositeBlockMock.getBlocks()).thenReturn(emptyBlockList);

        wall = new Wall(compositeBlockMock);

        //when
        List<Block> result = wall.findBlocksByMaterial("Material1");

        //then
        assertTrue(result.isEmpty(), "List should be empty when the list is empty");
    }

}
