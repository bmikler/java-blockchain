package blockchain;

import blockchain.mining.DifficultyManager;
import blockchain.mining.TimeCounter;
import blockchain.utils.FileService;
import java.io.Serializable;
import java.util.*;

public class BlockChain implements Serializable {

    private static final BlockChain INSTANCE = FileService.loadBlockChain()
            .orElse(new BlockChain(new ArrayList<>(), new DifficultyManager(30,5, new TimeCounter(), 0)));
    private final List<Block> blockchain;
    private final DifficultyManager difficultyManager;

    public BlockChain(List<Block> blockchain, DifficultyManager difficultyManager) {
        this.blockchain = blockchain;
        this.difficultyManager = difficultyManager;
    }


    public static BlockChain getInstance() {
        return INSTANCE;
    }

    public int getSize() {
        return blockchain.size();
    }

    public int getDifficulty() {
        return difficultyManager.getDifficult();
    }

    public synchronized void addBlockToBlockChain(Block block) {

        if(!isBlockValid(block)) {
            throw new IncorectBlockException();
        }

        blockchain.add(block);
        System.out.println(block);

        difficultyManager.update();

        save();
    }

    private Boolean isBlockValid(Block block) {

        return getLastHash().equals(block.getHashPrevious())
                && block.getHashCurrent().startsWith("0".repeat(getDifficulty()));

    }

    private void save() {
        FileService.saveBlockchain(INSTANCE);
    }

    public String getLastHash() {

        if(blockchain.isEmpty()){
            return String.valueOf(0);
        }
        return blockchain.get(blockchain.size() - 1).getHashCurrent();

    }

    public long getLastId() {
        if(blockchain.isEmpty()){
            return 0L;
        }
        return blockchain.get(blockchain.size() - 1).getId();
    }

}
