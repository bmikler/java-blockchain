package blockchain.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Blockchain implements Serializable {

    private final List<Block> blocks;
    private int difficulty = 0;

    public Blockchain(List<Block> blocks) {
        this.blocks = blocks;
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    public List<Block> getBlocks() {
        return Collections.unmodifiableList(blocks);
    }

    public Optional<Block> getLastBlock() {
        return blocks.isEmpty() ? Optional.empty() : Optional.of(blocks.get(blocks.size() - 1));
    }

    public int getLength() {
        return blocks.size();
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Blockchain{" +
                "blocks=" + blocks +
                '}';
    }
}
