package blockchain;

import blockchain.mining.DifficultyManager;
import blockchain.utils.FileService;

import java.io.Serializable;
import java.util.List;

public class BlockChain implements Serializable {

    private final List<Block> blockList;
    private final DifficultyManager difficultyManager;

    public BlockChain(List<Block> blockchain, DifficultyManager difficultyManager) {
        this.blockList = blockchain;
        this.difficultyManager = difficultyManager;
    }

    public int getSize() {
        return blockList.size();
    }

    public int getDifficulty() {
        return difficultyManager.getDifficult();
    }

    public synchronized void addBlockToBlockChain(Block block) {

        if(!isBlockValid(block)) {
            throw new IncorectBlockException();
        }

        blockList.add(block);
        System.out.println(block);

        difficultyManager.update();

        FileService.saveBlockchain(this);
    }

    private Boolean isBlockValid(Block block) {

        return getLastHash().equals(block.getHashPrevious())
                && block.getHashCurrent().startsWith("0".repeat(getDifficulty())) && block.getId() == getLastId() + 1;

    }

    public String getLastHash() {

        if(blockList.isEmpty()){
            return String.valueOf(0);
        }
        return blockList.get(blockList.size() - 1).getHashCurrent();

    }

    public long getLastId() {
        if(blockList.isEmpty()){
            return 0L;
        }
        return blockList.get(blockList.size() - 1).getId();
    }

}
