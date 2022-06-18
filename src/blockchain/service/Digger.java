package blockchain.service;

import blockchain.model.Block;
import blockchain.util.TimeCounter;

import java.util.Date;
import java.util.Random;

public class Digger {

    private final BlockchainService blockchainService;
    private final TimeCounter timeCounter;
    private final Random random;

    public Digger(BlockchainService blockchainService, TimeCounter timeCounter, Random random) {
        this.blockchainService = blockchainService;
        this.timeCounter = timeCounter;
        this.random = random;
    }

    public Block generateNextBlock() {

        timeCounter.start();

        Block newBlock = new Block();

        do {
            newBlock.setId(blockchainService.getLastBlock().getId() + 1);
            newBlock.setTimestamp(new Date().getTime());
            newBlock.setHashOfPrevious(blockchainService.getLastBlock().getHash());
            newBlock.setMagicNumber(random.nextLong());

        } while (!newBlock.getHash().startsWith("0".repeat(blockchainService.getActualDifficulty())));

        newBlock.setGeneratingTime(timeCounter.countTime());

        return newBlock;
    }
}
