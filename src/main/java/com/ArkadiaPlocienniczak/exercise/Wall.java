package com.ArkadiaPlocienniczak.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private CompositeBlock compositeBlock;
    private List<Block> blocks;

    public Wall(CompositeBlock compositeBlock) {
        this.compositeBlock = compositeBlock;
        this.blocks = compositeBlock.getBlocks();
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream()
                .filter(block -> block.getColor().equals(color))
                .findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> matchingBlocks = new ArrayList<>();
        for (Block block : blocks) {
            if (block.getMaterial().equals(material)) {
                matchingBlocks.add(block);
            }
        }
        return matchingBlocks;
    }

    @Override
    public int count() {
        return (blocks != null) ? blocks.size() : 0;
    }
}